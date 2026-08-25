# Pet Project Party Games
Et kartotek over selskabslege, kort-, terningespil og lign. Brugere kan tilpasse deres søgning efter deres aktuelle ønsker og behov, såsom antal spillere, varighed, nødvendige materialer, etc. Ved hvert spil findes regelsæt, bedømmelser og mulighed for at spille spillet digitalt.

## Målgruppe
- Unge og festglade personer, som deltager i fester — både med og uden alkohol
- Personer som leder efter eller mangler inspiration til sjove fest- og drukspil.
- Personer som ønsker en nem og hurtig måde at spille fest- og drukspil på, uden nødvendigvis at have fysiske spilmaterialer til rådighed.
- Personer som ønsker hurtigt at kunne komme i gang med et spil.
- Personer som ønsker at have adgang til fest- og drukspil direkte fra deres mobil eller computer.
- Målgruppen er overordnet set social og søger underholdning, som kan samle venner.
## Funktionelle krav
### Need-to-have
-	Brugeren skal kunne søge efter spil
-	Brugeren skal kunne tilføje forskellige filtre til søgningen
-	Brugeren skal præsenteres for en liste over spil, der passer deres filtre
-	Brugeren skal kunne se oplysninger om hvert spil
-	Brugeren skal kunne slå ”alkohol-regler” til og fra
-	Systemet skal registrere, hvor mange klik hvert spil får
### Nice-to-have
-	Brugeren skal kunne oprette en konto
-	Brugeren skal kunne logge ind
-	Brugeren skal kunne bedømme et spil
-	Brugeren skal kunne tilgå nødvendige materialer (kort, terningen, osv.) digitalt
-	Systemet skal præsentere spillene i oversigt med højest ratede spil først
-	Systemet skal give mulighed for at se mest populære spil baseret på denne uge, måned osv.
-	Brugeren skal kunne markere spil som favoritter
## Non-funktionelle krave
-	Oplysninger om spillene skal præsenteres ensartet
-	Tekst i brugerfladen skal være dansk
-	Brugernes adgangskoder må ikke gemmes i klar tekst
-	Systemet skal kunne udvides med flere spil senere

## User Stories
### US-1
#### Som bruger vil jeg kunne se en oversigt over spil, så jeg kan vælge et
-	Oversigten skal repræsentere de spil, der findes i databasen
-	Oversigten skal kunne inddeles i flere sider, hvis det e rnødvendigt
-	For hvert spil vises navn, et billede, antal spillere og kategori-ikoner
### US-2
#### Som bruger vil jeg kunne tilføje filtre til min oversigt, så jeg kan finde relevante spil
-	Brugeren skal kunne filtrere efter kategori
-   Brugeren skal kunne filtrere efter varighed
-	Brugeren skal kunne filtrere efter antal spillere
-	Oversigten skal opdateres i forhold til filtrene
-	Givet at, ingen spil passer til filtrene, når filtrene gives, så oplyses brugeren om dette
### US-3
#### Som bruger vil jeg kunne vælge et spil, så jeg kan se oplysninger om spillet
-	Når brugeren klikker på et spil, så vises navn, antal spillere, varighed, gennemsnitlig bedømmelse samt regelsæt (uden alkohol)
-   Når brugeren klikker på et spil, så opdateres antallet af registrerede klik på spillet
### US-4
#### Som bruger vil jeg kunne ændre reglerne, så jeg kan spille spillene som drukspil
-	Givet at, brugeren har valgt et spil, når brugeren vælger ”alkohol-regler” til, vises ét sæt regler
-	Givet at, brugeren har valgt et spil, når brugeren vælger ”alkohol-regler” fra, vises et andet sæt regler
### US-5
#### Som bruger vil jeg kunne sortere spiloversigten efter popularitet, navn eller varighed
-	Givet at, sorteringen er sat til ”popularitet”, når oversigten vises, så vil spil med flest klik vises øverst
-	Givet at, sorteringen er sat til ”alfabetisk”, når oversigten vises, så vil spil vises i alfabetisk rækkefølge
-	Givet at, sorteringen er sat til ”varighed”, når oversigten vises, så vil spil med kortest varighed vises øverst
-	Alle sorteringer skal kunne vises i omvendt rækkefølge
