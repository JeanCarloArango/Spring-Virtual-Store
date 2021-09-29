const ajaxCont = document.getElementById("ajax");
let alertSh = document.getElementById("alertCont");

// Cargar Formularios
const xhttpForms = new XMLHttpRequest;
xhttpForms.onload = function() {
	ajaxCont.innerHTML = this.responseText;
}

document.querySelectorAll(".link").forEach((el) => {
	el.addEventListener("click", e => {
		let id = e.target.getAttribute("id");
		xhttpForms.open("GET", id + ".html", true);
		xhttpForms.send();
	});
});

// Nombre archivo subido
const prLink = document.getElementById("Products_mgmt");

prLink.addEventListener("click", () => {
	setTimeout(() => {
		const inBtn = document.getElementById("file-input");
		const lblFile = document.getElementById("file-name");
		inBtn.addEventListener("change", () => {
			// console.log(inBtn.files[0].name);
			lblFile.textContent = inBtn.files[0].name;
		})
	}, 1000);
})

// Eventos AJAX
const usrsLink = document.getElementById("CRUD_Users");
const cstmrLink = document.getElementById("CRUD_Customers");
const supLink = document.getElementById("CRUD_Suppliers");

usrsLink.addEventListener("click", () => {
	setTimeout(() => {
		const usrsAddBtn = document.getElementById("usrAddBtn");
		const usrsUpBtn = document.getElementById("usrUpBtn");
		const usrsDelBtn = document.getElementById("usrDelBtn");
		usrsAddBtn.addEventListener("click", () => {
<<<<<<< HEAD
			submitCreateUser();
		});
		usrsUpBtn.addEventListener("click", () => {
			submitUpdateUser();
		});
		usrsDelBtn.addEventListener("click", () => {
			submitDelUser();
=======
			submitUser();
>>>>>>> branch 'master' of https://github.com/JeanCarloArango/Spring-Virtual-Store.git
		});
	}, 1000);
});

cstmrLink.addEventListener("click", () => {
	setTimeout(() => {
		const cstmrAddBtn = document.getElementById("cstmrAddBtn");
		const cstmrUpBtn = document.getElementById("cstmrUpBtn");
		const cstmrDelBtn = document.getElementById("cstmrDelBtn");
		cstmrAddBtn.addEventListener("click", () => {
			submitCreateCstmr();
		});
		cstmrUpBtn.addEventListener("click", () => {
			submitUpdateCstmr();
		});
		cstmrDelBtn.addEventListener("click", () => {
			submitDelCstmr();
		});
	}, 1000);
});

// Validaciones

// Usuarios
function validateUsr() {
	const usrDni = document.getElementById("txtDni");
	const usrName = document.getElementById("txtName");
	const usrEmail = document.getElementById("txtEmail");
	const usrNick = document.getElementById("txtUsr");
	const usrPass = document.getElementById("txtPass");

	hideErrors();
	if (usrDni.value.trim().length == 0) {
		usrDni.focus();
		shErrors("Cédula no puede estar vacío");
		return false;
	} else if (usrName.value.trim().length == 0) {
		usrName.focus();
		shErrors("Nombre no puede estar vacío");
		return false;
	} else if (usrEmail.value.trim().length == 0) {
		usrEmail.focus();
		shErrors("E-mail no puede estar vacío");
		return false;
	} else if (usrNick.value.trim().length == 0) {
		usrNick.focus();
		shErrors("Usuario no puede estar vacío");
		return false;
	} else if (usrPass.value.trim().length == 0) {
		usrPass.focus();
		shErrors("Contraseña no puede estar vacío");
		return false;
	} else {
		return true;
	}

}

// Clientes
function validateCstmr() {
	const cstmrDni = document.getElementById("txtDni");
	const cstmrName = document.getElementById("txtName");
	const cstmrAddr = document.getElementById("txtAddr");
	const cstmrPhone = document.getElementById("txtPhone");
	const cstmrEmail = document.getElementById("txtEmail");

	hideErrors();
	if (cstmrDni.value.trim().length == 0) {
		cstmrDni.focus();
		shErrors("Cédula no puede estar vacío");
		return false;
	} else if (cstmrName.value.trim().length == 0) {
		cstmrName.focus();
		shErrors("Nombre no puede estar vacío");
		return false;
	} else if (cstmrAddr.value.trim().length == 0) {
		cstmrAddr.focus();
		shErrors("Dirección no puede estar vacío");
		return false;
	} else if (cstmrPhone.value.trim().length == 0) {
		cstmrPhone.focus();
		shErrors("Teléfono no puede estar vacío");
		return false;
	} else if (cstmrEmail.value.trim().length == 0) {
		cstmrEmail.focus();
		shErrors("E-mail no puede estar vacío");
		return false;
	} else {
		return true;
	}

}

// Errors Dialogs
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

// Success Dialogs
function shSuccess(txtContent) {
	alertSh.innerHTML = "";
	let successCont = document.createElement("div");
	successCont.classList.add("success-cont");
	let successMsg = document.createElement("span");
	successMsg.classList.add("success-msg");
	let content = document.createTextNode(txtContent);
	successMsg.appendChild(content);
	successCont.appendChild(successMsg);
	alertSh.appendChild(successCont);
	document.querySelectorAll(".txt").forEach((el) => {
		el.value = "";
	});
}

// Submit Forms

<<<<<<< HEAD
// Usuarios
function submitCreateUser() {
	const usrDni = document.getElementById("txtDni").value.trim();
	const usrName = document.getElementById("txtName").value.trim();
	const usrEmail = document.getElementById("txtEmail").value.trim();
	const usrNick = document.getElementById("txtUsr").value.trim();
	const usrPass = document.getElementById("txtPass").value.trim();
	const xhttpServer = new XMLHttpRequest();
=======
function submitUser() {
	const usrDni = document.getElementById("txtDni").value.trim();
	const usrName = document.getElementById("txtName").value.trim();
	const usrEmail = document.getElementById("txtEmail").value.trim();
	const usrNick = document.getElementById("txtUsr").value.trim();
	const usrPass = document.getElementById("txtPass").value.trim();
>>>>>>> branch 'master' of https://github.com/JeanCarloArango/Spring-Virtual-Store.git
	let valid = validateUsr();
	if (valid) {
<<<<<<< HEAD
		var url = '/crearUsuario';
		var params = "userDni=" + usrDni + "&" + "userName=" + usrName + "&" + "userEmail=" + usrEmail + "&" + "userNick=" + usrNick + "&" + "userPass=" + usrPass;
		xhttpServer.open('POST', url, true);
=======
		var url = 'http://localhost:8080/crearUsuario?';
		var params = "userDni=" + usrDni + "&" + "userName=" + usrName + "&" + 
			"userEmail=" + usrEmail + "&" + "userNick=" + usrNick + "&" + "userPass=" +
		 	usrPass;
		xhttp.open('POST', url, true);
>>>>>>> branch 'master' of https://github.com/JeanCarloArango/Spring-Virtual-Store.git

		xhttpServer.setRequestHeader('Content-type',
			'application/x-www-form-urlencoded');

		xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
			if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
				shSuccess("Usuario creado satisfactoriamente");
				setTimeout(() => {
					alertSh.innerHTML = "";
				}, 4000);
			} else {
				shErrors("Datos no enviados");
			}
		}

		xhttpServer.send(params);

	}

	return;

}

function submitUpdateUser() {
	const usrDni = document.getElementById("txtDni").value.trim();
	const usrName = document.getElementById("txtName").value.trim();
	const usrEmail = document.getElementById("txtEmail").value.trim();
	const usrNick = document.getElementById("txtUsr").value.trim();
	const usrPass = document.getElementById("txtPass").value.trim();
	const xhttpServer = new XMLHttpRequest();
	let valid = validateUsr();
	if (valid) {
		var url = '/actualizarUsuario';
		var params = "userDni=" + usrDni + "&" + "userName=" + usrName + "&" + "userEmail=" + usrEmail + "&" + "userNick=" + usrNick + "&" + "userPass=" + usrPass;
		xhttpServer.open('POST', url, true);

		xhttpServer.setRequestHeader('Content-type',
			'application/x-www-form-urlencoded');

		xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
			if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
				shSuccess("Usuario actualizado con éxito");
				setTimeout(() => {
					alertSh.innerHTML = "";
				}, 4000);
			} else {
				shErrors("Datos no enviados");
			}
		}

		xhttpServer.send(params);

	}

	return;

}

function submitDelUser() {
	const usrDni = document.getElementById("txtDni").value.trim();
	const xhttpServer = new XMLHttpRequest();
	if (valid) {
		var url = '/eliminarUsuario';
		var params = "userDni=" + usrDni;
		xhttpServer.open('POST', url, true);

		xhttpServer.setRequestHeader('Content-type',
			'application/x-www-form-urlencoded');

		xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
			if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
				shSuccess("Usuario eliminado con éxito");
				setTimeout(() => {
					alertSh.innerHTML = "";
				}, 4000);
			} else {
				shErrors("Datos no enviados");
			}
		}

		xhttpServer.send(params);

	}

	return;

}

// Clientes
function submitCreateCstmr() {
	const cstmrDni = document.getElementById("txtDni").value.trim();
	const cstmrName = document.getElementById("txtName").value.trim();
	const cstmrAddr = document.getElementById("txtAddr").value.trim();
	const cstmrPhone = document.getElementById("txtPhone").value.trim();
	const cstmrEmail = document.getElementById("txtEmail").value.trim();
	const xhttpServer = new XMLHttpRequest();
	let valid = validateCstmr();
	if (valid) {
		var url = '/crearCliente';
		var params = "identifyCustomer=" + cstmrDni + "&" + "nameCustomer=" + cstmrName + "&" + "addressCustomer=" + cstmrAddr + "&" + "phoneCustomer=" + cstmrPhone + "&" + "emailCustomer=" + cstmrEmail;
		xhttpServer.open('POST', url, true);

		xhttpServer.setRequestHeader('Content-type',
			'application/x-www-form-urlencoded');

		xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
			if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
				shSuccess("Cliente creado satisfactoriamente");
				setTimeout(() => {
					alertSh.innerHTML = "";
				}, 4000);
			} else {
				shErrors("Datos no enviados");
			}
		}

		xhttpServer.send(params);

	}

	return;

}

function submitUpdateCstmr() {
	const cstmrDni = document.getElementById("txtDni").value.trim();
	const cstmrName = document.getElementById("txtName").value.trim();
	const cstmrAddr = document.getElementById("txtAddr").value.trim();
	const cstmrPhone = document.getElementById("txtPhone").value.trim();
	const cstmrEmail = document.getElementById("txtEmail").value.trim();
	const xhttpServer = new XMLHttpRequest();
	let valid = validateCstmr();
	if (valid) {
		var url = '/actualizarCliente';
		var params = "identifyCustomer=" + cstmrDni + "&" + "nameCustomer=" + cstmrName + "&" + "addressCustomer=" + cstmrAddr + "&" + "phoneCustomer=" + cstmrPhone + "&" + "emailCustomer=" + cstmrEmail;
		xhttpServer.open('POST', url, true);

		xhttpServer.setRequestHeader('Content-type',
			'application/x-www-form-urlencoded');

		xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
			if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
				shSuccess("Cliente actualizado satisfactoriamente");
				setTimeout(() => {
					alertSh.innerHTML = "";
				}, 4000);
			} else {
				shErrors("Datos no enviados");
			}
		}

		xhttpServer.send(params);

	}

	return;

}

function submitDelCstmr() {
	const cstmrDni = document.getElementById("txtDni").value.trim();
	const xhttpServer = new XMLHttpRequest();
	if (valid) {
		var url = '/eliminarCliente';
		var params = "identifyCustomer=" + cstmrDni;
		xhttpServer.open('POST', url, true);

		xhttpServer.setRequestHeader('Content-type',
			'application/x-www-form-urlencoded');

		xhttpServer.onreadystatechange = function() {//Call a function when the state changes.
			if (xhttpServer.readyState == 4 && xhttpServer.status == 200) {
				shSuccess("Cliente eliminado satisfactoriamente");
				setTimeout(() => {
					alertSh.innerHTML = "";
				}, 4000);
			} else {
				shErrors("Datos no enviados.");
			}
		}

		xhttpServer.send(params);

	}

	return;

}

=======
	
//  Esta vaina es lo que  edité ( por si se daña borrar)	

}
>>>>>>> branch 'master' of https://github.com/JeanCarloArango/Spring-Virtual-Store.git
