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

    const role=document.getElementById('reg-role').value;
    const username=document.getElementById('reg-username').value;
    const password=document.getElementById('reg-password').value;
    const fullName=document.getElementById('reg-fullname').value;
    const address=document.getElementById('reg-address').value;
    const phoneNumber=document.getElementById('reg-phone').value;

    const newUserData ={
        username: username,
        password: password,
        role: role,
        fullName: fullName,
        address: address,
        phoneNumber: phoneNumber
    };

    fetch('http://localhost:7070/api/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(newUserData)
    })
        .then(response => {
            if(response.status == 201){
                alert("Registration successful! Please log in.");
                document.getElementById('show-login').click();
            }
            else
            {
                alert("Registration failed. That username might already be taken.");
            }
        })
        .catch(error => console.error('Error connecting to server: ', error));
});

document.getElementById('login-form').addEventListener('submit', (e) => {
    e.preventDefault();
    console.log("Login button clicked! Ready to send data to Java.");
});