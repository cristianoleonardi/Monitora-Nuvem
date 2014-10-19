var i = 60; // segundos
function contagemRegressiva(){
if(i == 0){
        document.getElementById('cronometro').innerHTML = 'O tempo acabou!!';
        //location.reload();
        //location.href = "sendalertview";
}else{
        i--;
        document.getElementById('cronometro').innerHTML = i + ' segundos';
}
}
setInterval("contagemRegressiva()", 1000);