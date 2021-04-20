
# Mutant Seeker

Este proyecto detecta si un humano es mutante basándose en su secuencia de ADN.

## Instrucciones Ejecución de la API

**Url API** : http://mutantseekerel-env.eba-bqvha22b.us-east-1.elasticbeanstalk.com
+ **Operaciones**
    + **POST → /mutant/**
Al consumir esta operación podrás saber si la secuencia DNA que envías corresponde a un mutante o no.
**Ejemplo Body:**
`
{
“dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}
`

		**Posibles respuestas:**
		- **Http 200**: Para una secuencia DNA mutante
 		- **Http 403**: Para una secuencia DNA no mutante
 		- **Http 400**: Si envías una secuencia DNA inválida.
	 		Se considera inválida una solicitud que no cumpla las siguientes características:
	 		- La secuencia ADN solo puede contener las letras (A,T,C,G). No importa sin son letras
		 	   mayúsculas o minúsculas.
	 		- La secuencia ADN debe de ser un array de dimensiones NxN

    + **GET → /stats**
	    Al consumir esta operación podrás obtener una estadística de los humanos mutantes y no mutantes.