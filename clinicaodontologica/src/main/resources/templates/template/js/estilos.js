document.addEventListener("DOMContentLoaded", function() {
    // Obtiene todos los botones con la clase "btn-close"
    let btns = document.querySelectorAll(".btn-close");
  
   
    btns.forEach(function(btn) {
      btn.addEventListener("mouseover", function() {
        btn.style.backgroundColor = "#96d5852f";
        btn.style.color = "#c4eff30a";
      });
  
      btn.addEventListener("mouseout", function() {
        btn.style.backgroundColor = "";
        btn.style.color = "";
      });
    });
  });