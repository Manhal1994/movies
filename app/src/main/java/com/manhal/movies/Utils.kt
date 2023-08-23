package com.manhal.movies


fun formatToThreeDotDigit(number: Int): String {
    return "%,d".format(number)
}

fun fromMinToHourMinStyle(minutes: Int): String {
    val hours = minutes / 60
    val min = minutes - hours * 60
    if(hours>0 && min>0){
        return "${hours}h ${min}m"
    }
    else if(hours==0){
        return "${min}m"

    }
    else {
        return "${hours}h";
    }
}
