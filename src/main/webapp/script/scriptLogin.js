const log = document.getElementById("log");
const cancel = document.getElementById("cancel");
const alertSh = document.getElementById("alertCont");

log.addEventListener("click", () => {
	loginUser()
});

cancel.addEventListener("click", () => {
	//cancelLogin();
});

window.addEventListener("keyup", function(event) {
	if (event.keyCode === 13) {
		event.preventDefault()
		loginUser()
	}
});

// Error Dialogs

function shErrors(txtContent) {
	alertSh.innerHTML = "";
	let errorCont = document.createElement("div");
	errorCont.classList.add("error-cont");
	let errorMsg = document.createElement("span");
	errorMsg.classList.add("error-msg");
	let content = document.createTextNode(txtContent);
	errorMsg.appendChild(content);
	errorCont.appendChild(errorMsg);
	alertSh.appendChild(errorCont);
}

function hideErrors() {
	const inputs = document.querySelectorAll(".txt");
	// console.log(inputs);
	inputs.forEach((input) => {
		input.addEventListener("input", () => {
			alertSh.innerHTML = "";
		});
	});
}

// Login Functions

function loginUser() {
	const userNick = document.getElementById("txtName").value.trim();
	const userPass = document.getElementById("txtPass").value.trim();
	const xhttpServer = new XMLHttpRequest();

	let url = '/BraveTeamApp/loginUser';
	let params = "userNick=" + userNick + "&" + "userPass=" + userPass;
	xhttpServer.open('POST', url, true);

	xhttpServer.setRequestHeader('Content-type',
		'application/x-www-form-urlencoded');

	xhttpServer.send(params);

	xhttpServer.onreadystatechange = function(aEvt) {
		if (xhttpServer.readyState == 4) {
			if (xhttpServer.status == 200)
				if (xhttpServer.responseText == "true") {
					window.location.href = "/BraveTeamApp/Manage.html";
					localStorage.log = "V"
				}
				else {					
					shErrors("Usuario o Contraseña");
				}
			else {				
				console.log("Error loading page\n");
			}
		}
	};

	return;
}
