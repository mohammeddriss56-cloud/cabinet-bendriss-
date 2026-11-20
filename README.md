# 🫁 Cabinet Dr Bendriss – Gestion de cabinet médical

Application Android de gestion quotidienne d'un cabinet de **pneumologie et phtisiologie**.
Professionnelle, rapide, sécurisée, **100 % en français** et **hors connexion**.

![Kotlin](https://img.shields.io/badge/Kotlin-2.0-7F52FF?logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Material%203-4285F4?logo=jetpackcompose&logoColor=white)
![Room](https://img.shields.io/badge/Room-SQLite-green)
![Android](https://img.shields.io/badge/Android-8.0%2B-3DDC84?logo=android&logoColor=white)

---

## ✨ Fonctionnalités

### 1. Patients
- Ajout, modification, suppression (avec confirmation)
- Recherche par nom, prénom ou téléphone
- Numéro de dossier automatique
- Identité : nom, prénom, date de naissance, sexe, téléphone, adresse
- Antécédents médicaux et chirurgicaux, allergies, traitements habituels
- Historique : consultations, ordonnances, documents, paiements

### 2. Consultations
Motif, antécédents, histoire de la maladie, examen clinique, poids, taille, IMC (calcul automatique), tension artérielle, fréquence cardiaque, SpO2, auscultation pulmonaire, diagnostic, conduite à tenir, traitement, prochaine consultation.

### 3. Services et tarifs
Ajout / modification / suppression de prestations avec prix libre en **DA** (consultation spécialisée, contrôle, EFR, spirométrie, oxymétrie, autres actes).

### 4. Documents PDF (A4, impression et partage)
En-tête du cabinet, identité du patient, date automatique, signature et cachet.
- Ordonnance médicale
- Certificat d'arrêt de travail
- Certificat de bonne santé
- Demande de bilan biologique (liste personnalisable : NFS, CRP, VS, glycémie, urée/créatinine, bilan hépatique, ionogramme…)
- Demande d'imagerie (radio thorax face/profil, scanner, TDM thoracique HR…)
- Certificat médical, lettre d'orientation, compte rendu, demande d'avis spécialisé

### 5. Finances
Paiements automatiques, recettes quotidiennes, impayés, chiffre d'affaires, statistiques jour / mois / année, export **PDF et Excel**.

### 6. Tableau de bord
Total patients, consultations du jour, recettes du jour et du mois, derniers patients et dernières consultations.

### 7. Personnalisation
Nom du cabinet, médecin, spécialité, adresse, téléphone, logo, cachet, signature, tarifs, modèles d'ordonnance et de certificats.

### 8. Sauvegarde et sécurité
Base locale chiffrable, sauvegarde automatique, export / restauration, verrouillage par **code PIN**.

---

## 🛠️ Stack technique

| Élément | Choix |
|---|---|
| Langage | Kotlin |
| UI | Jetpack Compose + Material 3 |
| Base de données | Room (SQLite) |
| Architecture | MVVM + Repository |
| Injection | Hilt |
| Navigation | Navigation Compose |
| PDF | `android.graphics.pdf.PdfDocument` (natif) |
| Excel | Apache POI / export CSV compatible Excel |
| Sécurité | EncryptedSharedPreferences, BiometricPrompt (option) |
| Sauvegarde | WorkManager |

Aucun service payant, aucune connexion Internet requise.

---

## 📁 Structure du projet

```
app/src/main/java/dz/bendriss/cabinet/
├── data/
│   ├── local/        # Room : entités, DAO, base de données
│   └── repository/   # Repositories
├── domain/           # Modèles et cas d'utilisation
├── ui/
│   ├── dashboard/    # Tableau de bord
│   ├── patients/     # Liste, fiche, formulaire
│   ├── consultations/
│   ├── services/     # Services et tarifs
│   ├── documents/    # Générateurs PDF
│   ├── finance/      # Paiements, statistiques
│   ├── settings/     # Personnalisation, sécurité
│   └── theme/
├── pdf/              # Moteur de génération PDF
├── backup/           # Export / restauration
└── MainActivity.kt
```

---

## 🚀 Installation

### Prérequis
- Android Studio (Koala ou plus récent)
- JDK 17
- Android 8.0 (API 26) minimum

### Lancer le projet
```bash
git clone https://github.com/<votre-compte>/cabinet-bendriss.git
cd cabinet-bendriss
./gradlew assembleDebug
```
L'APK est généré dans `app/build/outputs/apk/debug/`.

### Générer l'APK avec GitHub Actions
Chaque *push* sur `main` compile l'application via `.github/workflows/android.yml`; l'APK est téléchargeable dans l'onglet **Actions → Artifacts**.

---

## 🗺️ Feuille de route

- [x] Cahier des charges
- [ ] Base de données Room et entités
- [ ] Gestion des patients
- [ ] Consultations
- [ ] Services et tarifs
- [ ] Génération PDF (ordonnance, certificats, bilans)
- [ ] Finances et exports
- [ ] Tableau de bord
- [ ] Personnalisation du cabinet
- [ ] Sauvegarde et code PIN
- [ ] Tests et publication de la v1.0

---

## 🔒 Confidentialité

Les données médicales restent **uniquement sur l'appareil**. Ne jamais publier de données réelles de patients sur GitHub.

## 📄 Licence

Usage privé – © Cabinet Dr Bendriss. Tous droits réservés.
