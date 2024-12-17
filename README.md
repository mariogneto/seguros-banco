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
