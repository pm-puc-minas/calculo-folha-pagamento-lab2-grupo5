
function navegarSuave(url) {
  const content = document.querySelector(".content");
  if (content) {
    content.classList.add("fade-out");
  }

  setTimeout(() => {
    window.location.href = url;
  }, 250);
}

function logout() {
  sessionStorage.clear();
  const content = document.querySelector(".content");
  if (content) {
    content.classList.add("fade-out");
  }
  setTimeout(() => {
    window.location.href = "login.html";
  }, 250);
}


window.addEventListener("DOMContentLoaded", () => {
  const content = document.querySelector(".content");
  if (content) {
    content.classList.remove("fade-out");
  }

  
  document.querySelectorAll(".sidebar a").forEach((link) => {
    const href = link.getAttribute("href");

    
    if (href && !link.classList.contains("logout")) {
      link.addEventListener("click", (e) => {
        e.preventDefault();
        navegarSuave(href);
      });
    }
  });
});
