drop table usuario;
drop table documento;
drop table perdocusuario;

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `login` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `documento` (
  `nome` varchar(45),
  `id` int(5) NOT NULL,
  `dataCri` varchar(45) DEFAULT NULL,
  `idUserDoc` int(5) references usuario(`id`),
  PRIMARY KEY (`id`)
);

CREATE TABLE `perdocusuario` (
  `idDoc` int(5) DEFAULT NULL REFERENCES documento(`id`),
  `idUsuario` int(5) DEFAULT NULL REFERENCES usuario(`id`),
  `perLeitura` binary(1) DEFAULT NULL,
  `perEditar` binary(1) DEFAULT NULL,
  `perExcluir` binary(1) DEFAULT NULL
);
