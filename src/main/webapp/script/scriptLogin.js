const log = document.getElementById("log");
const cancel = document.getElementById("cancel");

log.addEventListener("click", () => {
	loginUser()
});

cancel.addEventListener("click", () => {
	//cancelLogin();
});


function loginUser() {
	const userNick = document.getElementById("txtName").value.trim();
	const userPass = document.getElementById("txtPass").value.trim();
	const xhttpServer = new XMLHttpRequest();

	let url = '/TiendaVirtualApp/loginUser';
	let params = "userNick=" + userNick + "&" + "userPass=" + userPass;
	xhttpServer.open('POST', url, true);

	xhttpServer.setRequestHeader('Content-type',
		'application/x-www-form-urlencoded');

	xhttpServer.send(params);

	xhttpServer.onreadystatechange = (e) => {
		if (!xhttpServer.responseText) {
			console.log("Here")
		}
	}

	return;

}
