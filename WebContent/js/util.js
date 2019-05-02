function currentDate(){
    let d = new Date();
    let mon;
    let day;
    let hour;
    let min;
    let sec;
    //month
    if(d.getMonth() < 10){
        mon = "0"+d.getMonth();
    }else{
        mon = d.getMonth();
    }
    //day
    if(d.getDay() < 10){
        day = "0"+d.getDay();
    }else{
        day = d.getDay();
    }
    //hour
    if(d.getHours() < 10){
        hour = "0"+d.getHours();
    }else{
        hour = d.getHours();
    }
    //min
    if(d.getMinutes() < 10){
        min = "0"+d.getMinutes();
    }else{
        min = d.getMinutes();
    }
    //sec
    if(d.getSeconds() < 10){
        sec = "0"+d.getSeconds();
    }else{
        sec = d.getSeconds();
    }

    let time = d.getFullYear()+"-"+mon+"-"+day;
    return time;
}