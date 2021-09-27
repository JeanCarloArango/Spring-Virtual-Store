const ajaxCont = document.getElementById("ajax");
let alertSh = document.createElement("div");
alertSh.classList.add("alert-sh");;

// Cargar Formularios
const xhttp = new XMLHttpRequest;
xhttp.onload = function() {
	ajaxCont.innerHTML = this.responseText;
}

document.querySelectorAll(".link").forEach(el => {
	el.addEventListener("click", e => {
		let id = e.target.getAttribute("id");
		xhttp.open("GET", id + ".html", true);
		xhttp.send();
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
		usrsAddBtn.addEventListener("click", () => {
			submitUser();
		});
	}, 1000);
});

cstmrLink.addEventListener("click", () => {
	setTimeout(() => {
		const cstmrAddBtn = document.getElementById("cstmrAddBtn");
		cstmrAddBtn.addEventListener("click", () => {
			validateCstmr();
		});
	}, 1000);
});

supLink.addEventListener("click", () => {
	setTimeout(() => {
		const supAddBtn = document.getElementById("supAddBtn");
		supAddBtn.addEventListener("click", () => {
			errorSh.innerHTML = "";
			validateSup();
		});
	}, 1000);
});

// Validaciones

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
		shSuccess();
		return true;
	}

}

// Errors Dialogs

function shErrors(txtContent) {
	let errorCont = document.createElement("div");
	errorCont.classList.add("error-cont");
	let errorMsg = document.createElement("span");
	errorMsg.classList.add("error-msg");
	let content = document.createTextNode(txtContent);
	errorMsg.appendChild(content);
	errorCont.appendChild(errorMsg);
	alertSh.appendChild(errorCont)
	ajaxCont.appendChild(alertSh);
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

function shSuccess() {
	alertSh.innerHTML = "";
	let successCont = document.createElement("div");
	successCont.classList.add("success-cont");
	let successMsg = document.createElement("span");
	successMsg.classList.add("success-msg");
	let content = document.createTextNode("Datos enviados con éxito");
	successMsg.appendChild(content);
	successCont.appendChild(successMsg);
	alertSh.appendChild(successCont)
	ajaxCont.appendChild(alertSh);
	const inputs = document.querySelectorAll(".txt");
	// console.log(inputs);
	inputs.forEach((input) => {
		input.value = "";
	});
}

// Submit Forms

function submitUser() {
	const usrDni = document.getElementById("txtDni").value.trim();
	const usrName = document.getElementById("txtName").value.trim();
	const usrEmail = document.getElementById("txtEmail").value.trim();
	const usrNick = document.getElementById("txtUsr").value.trim();
	const usrPass = document.getElementById("txtPass").value.trim();
	let valid = validateUsr();
	if (valid) {
		var url = 'http://localhost:8080/crearUsuario?';
		var params = "userDni=" + usrDni + "&" + "userName=" + usrName + "&" + 
			"userEmail=" + usrEmail + "&" + "userNick=" + usrNick + "&" + "userPass=" +
		 	usrPass;
		xhttp.open('POST', url, true);

		xhttp.setRequestHeader('Content-type',
			'application/x-www-form-urlencoded');

		xhttp.onreadystatechange = function() {//Call a function when the state changes.
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				shSuccess();
			}
		}
		xhttp.send(params);
	}
}