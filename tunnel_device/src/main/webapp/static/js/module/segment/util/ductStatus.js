var param={
    segmentDuct:{START_DUCT_CODE:36,END_DUCT_CODE:1},
    faultDuct:[{START_DUCT_CODE:5,END_DUCT_CODE:16},{START_DUCT_CODE:6,END_DUCT_CODE:2},{START_DUCT_CODE:5,END_DUCT_CODE:5}],
    sandyDate:[{START_DUCT_CODE:2,END_DUCT_CODE:7},{START_DUCT_CODE:33,END_DUCT_CODE:16},{START_DUCT_CODE:5,END_DUCT_CODE:7}]
}

function f(obj) {
    var num = obj.segmentDuct.START_DUCT_CODE-obj.segmentDuct.END_DUCT_CODE;
    // console.log(num)
}
f(param)