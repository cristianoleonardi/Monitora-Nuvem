function timer() {
    
}
var i = 10; // segundos
 
function contagemRegressiva() {
   
    if (i == 0) {
        document.getElementById('cronometro').innerHTML = 'O tempo acabou!!';
    } else {
        i--;
        document.getElementById('cronometro').innerHTML = i + ' segundos';
    }
}