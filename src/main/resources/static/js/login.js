// ===============================
// VaultX Login JavaScript
// ===============================

// ---------- Show / Hide Password ----------

const passwordInput = document.getElementById("password");
const togglePassword = document.getElementById("togglePassword");

if (togglePassword && passwordInput) {

    togglePassword.addEventListener("click", () => {

        const type = passwordInput.getAttribute("type") === "password"
            ? "text"
            : "password";

        passwordInput.setAttribute("type", type);

        togglePassword.classList.toggle("fa-eye");
        togglePassword.classList.toggle("fa-eye-slash");

    });

}

// ---------- Login Validation ----------

const loginForm = document.querySelector("form");

if (loginForm) {

    loginForm.addEventListener("submit", function (e) {

        e.preventDefault();

        const email = document.querySelector("input[type='email']");
        const password = document.getElementById("password");
        const button = document.querySelector(".login-btn");

        if (email.value.trim() === "") {

            alert("Please enter your email.");
            email.focus();
            return;

        }

        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (!emailRegex.test(email.value.trim())) {

            alert("Please enter a valid email.");
            email.focus();
            return;

        }

        if (password.value.trim() === "") {

            alert("Please enter your password.");
            password.focus();
            return;

        }

        if (password.value.length < 6) {

            alert("Password must contain at least 6 characters.");
            password.focus();
            return;

        }

        // Loading Animation

        button.disabled = true;

        button.innerHTML =
            '<i class="fa-solid fa-spinner fa-spin"></i> Signing In...';

        setTimeout(() => {

            loginForm.submit();

        }, 1200);

    });

}

// ---------- Input Focus Animation ----------

const inputs = document.querySelectorAll("input");

inputs.forEach(input => {

    input.addEventListener("focus", () => {

        input.parentElement.style.transform = "scale(1.02)";

    });

    input.addEventListener("blur", () => {

        input.parentElement.style.transform = "scale(1)";

    });

});