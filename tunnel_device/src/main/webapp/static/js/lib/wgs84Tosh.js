// 经纬度坐标转换为上海地方坐标

function wgs84Tosh(lat, lon) {
    var xx, yy, r2d, tolat, tolon, rearth, PI;
    PI = 3.141592653589793;
    r2d = 57.2957795131;
    tolat = (31 + (14.0 + 7.55996 / 60.0) / 60.0) / r2d;
    tolon = (121.0 + (28.0 + 1.80651 / 60.0) / 60) / r2d;
    rearth = 6371006.84;
    var hor, frlat, frlon, gcdist, clatf, clatt, slatf, slatt, gcbrg;
    var dlon, cdlon, sdlon, sdist, cdist, sbrg, cbrg, temp;
    var intlat, intlon;
    intlat = lat;
    intlon = lon;
    frlat = lat / r2d;
    frlon = lon / r2d;
    clatt = Math.cos(frlat);
    clatf = Math.cos(tolat);
    slatt = Math.sin(frlat);
    slatf = Math.sin(tolat);
    dlon = frlon - tolon;
    cdlon = Math.cos(dlon);
    sdlon = Math.sin(dlon);
    cdist = slatf * slatt + clatf * clatt * cdlon;
    temp = (clatt * sdlon) * (clatt * sdlon) + (clatf * slatt - slatf * clatt * cdlon) * (clatf * slatt - slatf * clatt * cdlon);
    sdist = Math.sqrt(Math.abs(temp));
    if ((Math.abs(sdist) > 1e-7) || (Math.abs(cdist) > 1e-7))
        gcdist = Math.atan2(sdist, cdist);
    else
        gcdist = 0;
    sbrg = sdlon * clatt;
    cbrg = (clatf * slatt - slatf * clatt * cdlon);
    if ((Math.abs(sbrg) > 1e-7) || (Math.abs(cbrg) > 1e-7)) {
        temp = Math.atan2(sbrg, cbrg);
        while (temp < 0) {
            temp = temp + 2 * PI;
            gcbrg = temp;
        }
    } else
        gcbrg = 0;
    hor = gcdist * rearth;
    xx = hor * Math.sin(temp);
    yy = hor * Math.cos(temp);
    return [xx, yy];
}