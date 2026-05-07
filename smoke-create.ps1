param(
  [int]$Port = 9090,
  [string]$JarPath = "target\springboot_proj-0.0.1-SNAPSHOT.jar",
  [int]$WaitSeconds = 8
)

if (-not (Test-Path $JarPath)) {
  Write-Host "Jar not found -> building (skip tests)..."
  & .\mvnw.cmd -DskipTests clean package
  if ($LASTEXITCODE -ne 0) { throw "Build failed" }
} else {
  Write-Host "Jar found: $JarPath"
}

Write-Host "Starting app..."
$proc = Start-Process -FilePath "java" -ArgumentList "-jar", $JarPath -PassThru
Start-Sleep -Seconds $WaitSeconds

$payload = @{
  immatriculation = "TEST-IMMAT-" + (Get-Random -Maximum 9999)
  modele = "Clio Test"
  type = "VOITURE"
  kilometrage = 0
  statut = "DISPONIBLE"
}
$body = $payload | ConvertTo-Json

$uri = "http://localhost:$Port/api/vehicules"
Write-Host "POST $uri"
try {
  $resp = Invoke-RestMethod -Uri $uri -Method Post -Body $body -ContentType "application/json" -TimeoutSec 10 -ErrorAction Stop
  $resp | ConvertTo-Json -Depth 5
} catch {
  Write-Host "HTTP request failed. Full error:"
  $_ | Format-List -Force
  Write-Host "`nCheck the server console for the Java stacktrace."
}
Write-Host "`nTo stop the app: Stop-Process -Id $($proc.Id) -Force"
