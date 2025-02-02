
function initajax(){
    return new XMLHttpRequest();
}

function resultado(){
    /*
    if (ajax.readyState==4 && ajax.status==200)
        document.getElementById("ajax").innerHTML=ajax.responseText;
    else document.getElementById("ajax").innerHTML="erro";*/
}

function atualizar(){
    document.getElementById("ajax").innerHTML="funfou";
/*
    ajax=initajax();
    dado="tn1="+document.getElementById("tn1").value+"&";
    dado+="tn2="+document.getElementById("tn2").value+"&";
    dado+="soperacao="+document.getElementById("opera").value;

    if (ajax){
        ajax.onreadystatechange=function(){
            if (ajax.readyState==4 && ajax.status==200)
                document.getElementById("ajax").innerHTML=ajax.responseText;
            else document.getElementById("ajax").innerHTML="erro";
        };
        url="calculadoraservletajax?"+dado;
        ajax.open('GET',url,true);
        ajax.send(null);
    }*/
}
