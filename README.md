Estrutura Geral


Tecnologias a serem utilizadas:
Framework: Spring Boot.

Banco de Dados: PostgreSQL ou MongoDB.
Resiliência: Resilience4J.

Testes: JUnit.
Documentação: Swagger/OpenAPI.

Arquitetura:
Hexagonal (Ports and Adapters):

Separar as responsabilidades em camadas:

Domínio: Regras de negócio.

Aplicação: Orquestração e lógica de uso.

Infraestrutura: Conexões externas (Banco, APIs, etc.).

APIs a serem criadas:

API de Cadastro: CRUD completo (criação, leitura, atualização, deleção).

API de Seguros:

Simulação de seguros (consulta baseada no cliente e tipo de seguro).

Contratação do seguro (verificando a existência do cliente via API de Cadastro).


API de Seguros


Responsável por simular e contratar seguros.


Endpoints:

POST /seguros/simular: Retorna valores simulados para Bronze, Prata e Ouro.

POST /seguros/contratar: Contrata o seguro e confirma o cadastro do cliente na API de Cadastro.

Fluxo para contratação:

A API de Seguros deve realizar uma chamada à API de Cadastro para confirmar que o cliente existe e está ativo antes de prosseguir com a contratação.


Modelo de Dados (Seguro):

public class Seguro {
    private String cpfCliente;
    private TipoSeguro tipoSeguro; // BRONZE, PRATA, OURO
    private BigDecimal valorContratado;
    private LocalDate dataContratacao;
}


public enum TipoSeguro {
    BRONZE, PRATA, OURO
}
