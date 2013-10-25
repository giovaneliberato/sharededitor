
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `nomeUsuario` varchar(45) NOT NULL,
  `setorUsuario` varchar(45) NOT NULL,
  `cargoUsuario` varchar(45) NOT NULL,
  `emailUsuario` varchar(45) NOT NULL,
  PRIMARY KEY (`idUsuario`)
);

CREATE TABLE `documento` (
  `nomeDoc` varchar(45),
  `idDoc` int(5) NOT NULL,
  `dataCri` varchar(45) DEFAULT NULL,
  `idUserDoc` int(5) DEFAULT NULL,
  PRIMARY KEY (`idDoc`)
);

CREATE TABLE `perdocusuario` (
  `idDoc` int(5) DEFAULT NULL,
  `idUsuario` int(5) DEFAULT NULL,
  `perLeitura` binary(1) DEFAULT NULL,
  `perEditar` binary(1) DEFAULT NULL,
  `perExcluir` binary(1) DEFAULT NULL
);
