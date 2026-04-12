# API Flotte - Spring Boot

Ce projet expose des APIs REST pour la gestion d'une flotte: vehicules, chauffeurs, missions, consommations, maintenance preventive et tableaux de bord.

## Endpoints principaux

- `GET /api/vehicules`
- `GET /api/vehicules/{id}`
- `POST /api/vehicules`
- `PUT /api/vehicules/{id}`
- `DELETE /api/vehicules/{id}`
- `GET /api/vehicules/maintenance/alerts?thresholdKm=10000`

- `GET /api/chauffeurs`
- `GET /api/chauffeurs/{id}`
- `POST /api/chauffeurs`
- `PUT /api/chauffeurs/{id}`
- `DELETE /api/chauffeurs/{id}`

- `GET /api/missions`
- `GET /api/missions/{id}`
- `POST /api/missions`
- `PUT /api/missions/{id}`
- `PUT /api/missions/{id}/affectation`
- `DELETE /api/missions/{id}`

- `GET /api/consommations`
- `GET /api/consommations/{id}`
- `POST /api/consommations`
- `PUT /api/consommations/{id}`
- `DELETE /api/consommations/{id}`

- `GET /api/dashboard/fuel-costs`
- `GET /api/dashboard/vehicules/most-active?limit=5`

## Exemples JSON

### Creer un vehicule
```json
{
  "immatriculation": "123-TN-456",
  "modele": "Toyota Hilux",
  "type": "Pickup",
  "kilometrage": 125000,
  "statut": "EN_SERVICE"
}
```

### Creer une mission (affectation chauffeur + vehicule)
```json
{
  "vehiculeId": 1,
  "chauffeurId": 2,
  "pointDepart": "Tunis",
  "destination": "Sfax",
  "distance": 270.5
}
```

### Mettre a jour l'affectation d'une mission
```json
{
  "vehiculeId": 3,
  "chauffeurId": 5
}
```

### Enregistrer une consommation
```json
{
  "vehiculeId": 1,
  "date": "2026-04-12",
  "quantiteCarburant": 42.70,
  "coutTotal": 145.90
}
```

