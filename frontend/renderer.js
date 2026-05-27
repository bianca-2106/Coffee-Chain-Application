const loginView = document.getElementById('login-view');
const registerView = document.getElementById('register-view');

document.getElementById('show-register').addEventListener('click', (e) => {
    e.preventDefault();
    loginView.classList.remove('active');
    registerView.classList.add('active');
});

document.getElementById('show-login').addEventListener('click', (e) => {
    e.preventDefault();
    registerView.classList.remove('active');
    loginView.classList.add('active');
});

document.getElementById('register-form').addEventListener('submit', (e) => {
    e.preventDefault();
    console.log("Registration button clicked! Ready to send data to Java.");
});

document.getElementById('login-form').addEventListener('submit', (e) => {
    e.preventDefault();
    console.log("Login button clicked! Ready to send data to Java.");
});