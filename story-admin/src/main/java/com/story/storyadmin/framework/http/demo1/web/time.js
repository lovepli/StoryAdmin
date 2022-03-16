(function(){
setInterval(() =>{
document.getElementById('time').innerText = new Date().toLocaleTimeString();
},1000);
})();