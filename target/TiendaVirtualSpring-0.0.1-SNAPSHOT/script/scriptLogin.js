const log = document.getElementById("log");
const cancel = document.getElementById("cancel");

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
					alert("Error el usuario o contraseña estan erroneos")
				}
			else {				
				console.log("Error loading page\n");
			}
		}
	};

	return;
}
