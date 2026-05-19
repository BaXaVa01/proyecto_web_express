// Interactividad básica para Tienda Web Express

// Contador de productos seleccionados (simulado)
let carritoCount = 0;

function agregarAlCarrito() {
    carritoCount++;
    alert("¡Producto agregado al carrito de forma simulada!");
    console.log("Productos en carrito:", carritoCount);
    
    // Si tuviéramos un elemento en el DOM para mostrar el contador:
    const cartBadge = document.querySelector('.cart-badge');
    if (cartBadge) {
        cartBadge.innerText = carritoCount;
        cartBadge.style.display = 'block';
    }
}

// Validación básica del formulario de pedido
function validarFormulario() {
    const nombre = document.getElementById('nombreCliente').value;
    const correo = document.getElementById('correo').value;
    const cantidad = document.getElementById('cantidad').value;

    if (nombre.trim() === "") {
        alert("Por favor, ingresa tu nombre.");
        return false;
    }

    if (correo.trim() === "" || !correo.includes('@')) {
        alert("Por favor, ingresa un correo electrónico válido.");
        return false;
    }

    if (cantidad <= 0) {
        alert("La cantidad debe ser al menos 1.");
        return false;
    }

    return true;
}

// Ejemplo de mostrar/ocultar (opcional)
function toggleDetalles(id) {
    const el = document.getElementById('detalles-' + id);
    if (el) {
        el.style.display = el.style.display === 'none' ? 'block' : 'none';
    }
}

console.log("App JS cargado correctamente.");
