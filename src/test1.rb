print "123\n"


#  ----------------------------- AEROC -------------------------------------------
#  Toode                Pikkus (mm) Laius (mm) Kõrgus (mm) Tihedus (kg/m³) Kaal* (kg)
#  EcoTerm Plus 500     600         500         200         300       19,8
#  EcoTerm Plus 375     600         375         200         300       14,9
#  EcoTerm Plus 300     600         300         200         300       11,9
# Universal  200/300    600         200 / 300   300 / 200   375       14,9
# Classic 300           600         300         200         425       16,8
# Classic 250           600         250         200         425       14,0
# Classic 200           600         200         200         425       11,2

# kaal* - 1,1 kordne kuivtihedus mida kasutatakse arvutustes omakaalu normväärtusena


# ???   UKS, 900x2100
# ???   VUNDAMENTI KÕRGUS, TALDMIK LAIUS
# Sillused vs plokk

startingPointX  = 2
startingPointY  = 2

internalWidth   = 4       # depends on block count
internalLength  = 5       # depends on block count
wallWidth       = 0.375

zeroGroundLevel = 0.3
wallHeight      = 2.5     # depends on block count

print wallHeight
