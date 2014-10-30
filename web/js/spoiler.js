clickSpoilerCustom = function clickSpoilerCustom(el, className) {//клик по dl, dt, dd
    el = el.parentNode;
    var m, k, s;
    s = el.getElementsByTagName("dd")[0].style.display;
    m = document.getElementsByTagName("dl");
    k = m.length;

    while (k--) {
        if (m[k].className == className) {//
            m[k].getElementsByTagName("dd")[0].style.display = "none";
        }
    }
    if (s == "none" || s == "") {
        el.getElementsByTagName("dd")[0].style.display = "block";
//
    }
    else {
        el.getElementsByTagName("dd")[0].style.display = "none";
    }
}

clickSpoilerSub = function clickSpoilerSub(el) {//клик по dl, dt, dd
    clickSpoilerCustom(el, "spoilerSub")
}

clickSpoiler = function clickSpoiler(el) {
    clickSpoilerCustom(el, "spoiler")
}