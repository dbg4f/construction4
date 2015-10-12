# construction4

72701:002:2219

http://www.maaamet.ee/ky/FindKYbyT.asp?txtCU=72701:002:2219

H27.5

Lattitude 59 deg North, Longitude 24 deg East

B 59d 21' 44.18''   L 24d 34' 26.94'' 

4443 2841

P 4431 2848

P 43.6  2894

P 4287 2941

4234 2975

4213 2852

4231 28.4''

4225 2800

4365 2708

4375 2721


57.7017 212.9543

41.7994 259.3718

P 49.1184 261.9696

P 93.2424 276.6721

P 137.7829 292.2484

170.8253 302.9887

184.1104 263.9485

173.0395 260.1979

176.7866 247.7527


90.1431 217.6963

84.1818 221.7878





http://gis.stackexchange.com/questions/2951/algorithm-for-offsetting-a-latitude-longitude-by-some-amount-of-meters

If your displacements aren't too great (less than a few kilometers) and you're not right at the poles, use the quick and dirty estimate that 111,111 meters (111.111 km) in the y direction is 1 degree (of latitude) and 111,111 * cos(latitude) meters in the x direction is 1 degree (of longitude).

1 deg lat = 111111m
1 deg lon = 111111 * cos(59deg) [59d = 1.03, cos(1.03) = 0.514819] = 57202

1 deg = 60min
1 min = 60 sek
1 deg = 3600 sek

1 sek lat = 111111/3600 = 30,864166667m
1 sek lon = 57202/3600 = 15,889444444m

44.43 28.41
42.34 29.75

2.09 1.34


        x = 2.09 x 30.864166667;

        y = 1.34 x 15.889444444;

        Math.sqrt(x2 + y2) = 67.92




Viimane nurk: 67.66 m
Viimane nurk: 161.16 °

Viimane nurk: 72.5 °
Viimane lõik: 26.86 m










