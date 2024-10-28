CREATE TABLE Persona (
    idPersona INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) NOT NULL,
    nombreCompleto VARCHAR(100) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    correo VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Empleado (
    idEmpleado INT AUTO_INCREMENT PRIMARY KEY,
    idPersona INT,
    estado ENUM('ACTIVO', 'INACTIVO') NOT NULL,
    nombreUsuario VARCHAR(50) NOT NULL,
    contrasenha VARCHAR(255) NOT NULL,
    salario DOUBLE NOT NULL,
    fechaContratacion DATE NOT NULL,
    FOREIGN KEY (idPersona) REFERENCES Persona(idPersona) ON DELETE CASCADE,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Almacen (
    idAlmacen INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    estado VARCHAR(50) NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Administrador (
    idAdministrador INT AUTO_INCREMENT PRIMARY KEY,
    idEmpleado INT,
    idAlmacen INT,
    FOREIGN KEY (idEmpleado) REFERENCES Empleado(idEmpleado) ON DELETE CASCADE,
    FOREIGN KEY (idAlmacen) REFERENCES Almacen(idAlmacen) ON DELETE SET NULL,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE TrabajadorDeAlmacen (
    idTrabajador INT AUTO_INCREMENT PRIMARY KEY,
    idEmpleado INT,
    licenciaMontacarga BOOLEAN NOT NULL,
    idAlmacen INT,
    FOREIGN KEY (idEmpleado) REFERENCES Empleado(idEmpleado) ON DELETE CASCADE,
    FOREIGN KEY (idAlmacen) REFERENCES Almacen(idAlmacen) ON DELETE SET NULL,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Vendedor (
    idVendedor INT AUTO_INCREMENT PRIMARY KEY,
    idEmpleado INT,
    ingresosVentas DOUBLE NOT NULL,
    porcentajeComision DOUBLE NOT NULL,
    FOREIGN KEY (idEmpleado) REFERENCES Empleado(idEmpleado) ON DELETE CASCADE,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Empresa (
    idEmpresa INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL,
    tipoIndustria VARCHAR(50) NOT NULL,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Representante (
    idRepresentante INT AUTO_INCREMENT PRIMARY KEY,
    idPersona INT,
    idEmpresa INT,
    FOREIGN KEY (idPersona) REFERENCES Persona(idPersona) ON DELETE CASCADE,
    FOREIGN KEY (idEmpresa) REFERENCES Empresa(idEmpresa) ON DELETE CASCADE,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Proveedor (
    idProveedor INT AUTO_INCREMENT PRIMARY KEY,
    idEmpresa INT,
    fecha_afiliacion DATE NOT NULL,
    RUC VARCHAR(50) NOT NULL UNIQUE,
    razonSocial VARCHAR(100) NOT NULL,
    calificacion DOUBLE,
    pais VARCHAR(50),
    FOREIGN KEY (idEmpresa) REFERENCES Empresa(idEmpresa) ON DELETE CASCADE,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Cliente (
    idCliente INT AUTO_INCREMENT PRIMARY KEY,
    idEmpresa INT,
    fechaRegistro DATE NOT NULL,
    fechaUltimaCompra DATE NOT NULL,
    FOREIGN KEY (idEmpresa) REFERENCES Empresa(idEmpresa) ON DELETE CASCADE,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Visita (
    idVisita INT AUTO_INCREMENT PRIMARY KEY,
    idVendedor INT,
    fecha DATE NOT NULL,
    duracion TIME NOT NULL,
    idCliente INT,
    FOREIGN KEY (idVendedor) REFERENCES Vendedor(idVendedor) ON DELETE CASCADE,
    FOREIGN KEY (idCliente) REFERENCES Cliente(idCliente) ON DELETE SET NULL,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Producto (
    idProducto INT AUTO_INCREMENT PRIMARY KEY,          
    nombreProducto VARCHAR(100) NOT NULL,
    precio DOUBLE NOT NULL,
    stockMinimo INT NOT NULL,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE ProductoAlmacenado (
    idProductoAlmacenado INT AUTO_INCREMENT PRIMARY KEY,
    idProducto INT,
    idAlmacen INT,
    fechaAlmacenado DATE NOT NULL,
    stockActual INT NOT NULL,
    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto) ON DELETE CASCADE,
    FOREIGN KEY (idAlmacen) REFERENCES Almacen(idAlmacen) ON DELETE CASCADE,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE Documento (
    idDocumento INT AUTO_INCREMENT PRIMARY KEY,
    idEmpresa INT,
    estado ENUM('SOLICITUD', 'COTIZACION', 'ANULADO', 'PAGADO') NOT NULL,
    tipo ENUM('COMPRA', 'VENTA', 'FACTURA') NOT NULL,
    idVendedor INT,
    idAdministrador INT,
    idTrabajadorDeAlmacen INT,
    FOREIGN KEY (idEmpresa) REFERENCES Empresa(idEmpresa) ON DELETE CASCADE,
    FOREIGN KEY (idVendedor) REFERENCES Vendedor(idVendedor) ON DELETE SET NULL,
    FOREIGN KEY (idAdministrador) REFERENCES Administrador(idAdministrador) ON DELETE SET NULL,
    FOREIGN KEY (idTrabajadorDeAlmacen) REFERENCES TrabajadorDeAlmacen(idTrabajador) ON DELETE SET NULL,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);

CREATE TABLE LineaDocumento (
    idLinea INT AUTO_INCREMENT PRIMARY KEY,
    idDocumento INT,
    idProducto INT,
    cantidad INT NOT NULL,
    precioUnitario DOUBLE NOT NULL,
    FOREIGN KEY (idDocumento) REFERENCES Documento(idDocumento) ON DELETE CASCADE,
    FOREIGN KEY (idProducto) REFERENCES Producto(idProducto) ON DELETE CASCADE,
    
    -- Eliminación lógica
    eliminado BOOLEAN DEFAULT FALSE  -- Columna para eliminación lógica
);