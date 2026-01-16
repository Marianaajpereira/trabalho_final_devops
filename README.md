# 10cdlr-devops-dev

Projeto de demonstração com Spring Boot para gerenciamento de usuários, incluindo autenticação e autorização.

## Descrição

Esta é uma aplicação Spring Boot que implementa um sistema de gerenciamento de usuários com recursos de segurança, autenticação e controle de acesso. O projeto utiliza Java 11 e inclui testes automatizados (funcionais e integrados) com pipeline CI/CD no Azure Pipelines.

## Tecnologias Utilizadas

- **Framework**: Spring Boot 2.7.14
- **Linguagem**: Java 11
- **Banco de Dados**: H2 (em memória/arquivo)
- **Build Tool**: Maven
- **Segurança**: Spring Security
- **ORM**: Spring Data JPA (Hibernate)
- **CI/CD**: Azure Pipelines
- **Qualidade de Código**: SonarCloud

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/example/demo/
│   │   ├── DemoApplication.java          # Aplicação principal
│   │   ├── configuration/                # Configurações de segurança
│   │   │   ├── AppUser.java
│   │   │   ├── LoggedInUser.java
│   │   │   ├── SecurityConfiguration.java
│   │   │   └── UserAuthService.java
│   │   └── user/                         # Módulo de usuários
│   │       ├── User.java
│   │       ├── UserAuthorizationService.java
│   │       ├── UserController.java
│   │       ├── UserRepository.java
│   │       └── UserService.java
│   └── resources/
│       ├── application.properties         # Configurações da aplicação
│       └── scripts/                       # Scripts de teste
├── scripts/
│   ├── smoketest.sh                     # Teste de saúde
│   ├── testeIntegrado.sh                # Testes integrados
│   └── testesFuncionais.sh              # Testes funcionais
```

## Dependências Principais

- `spring-boot-starter-web` - Desenvolvimento de aplicações web REST
- `spring-boot-starter-security` - Segurança e autenticação
- `spring-boot-starter-data-jpa` - Acesso a dados com JPA/Hibernate
- `h2` - Banco de dados H2
- `spring-boot-devtools` - Ferramentas de desenvolvimento

## Pré-requisitos

- Java 11+
- Maven 3.6+
- Git

## Instalação e Configuração

### 1. Clonar o Repositório

```bash
git clone <url-do-repositorio>
cd trabalho_final_devops
```

### 2. Configurar Propriedades

O arquivo `application.properties` contém as configurações padrão:

```properties
spring.datasource.url=jdbc:h2:./temp
spring.datasource.username=sa
spring.jpa.hibernate.ddl-auto=update
server.port=5000
```

### 3. Compilar o Projeto

```bash
# Linux/Mac
./mvnw clean install

# Windows
mvnw.cmd clean install
```

## Executando a Aplicação

### Via Maven

```bash
./mvnw spring-boot:run
```

A aplicação estará disponível em `http://localhost:5000`

## Endpoints Principais

### Health Check
- **GET** `/` - Verifica o status da aplicação
  ```bash
  curl http://localhost:5000/
  ```
  Resposta: `HEALTH CHECK OK!`

### Usuários
- **POST** `/api/1.0/users` - Criar novo usuário
  ```bash
  curl -X POST http://localhost:5000/api/1.0/users \
    -H "Content-Type: application/json" \
    -d '{"username":"user","email":"user@example.com"}'
  ```

- **PUT** `/api/1.0/users/{id}` - Atualizar usuário
  ```bash
  curl -X PUT http://localhost:5000/api/1.0/users/1 \
    -H "Content-Type: application/json" \
    -H "Authorization: Bearer <token>" \
    -d '{"username":"newname","email":"newemail@example.com"}'
  ```

### Secured Endpoint
- **GET** `/secured` - Endpoint protegido por autenticação
  ```bash
  curl -X GET http://localhost:5000/secured \
    -H "Authorization: Bearer <token>"
  ```

## Segurança

### Spring Security
O projeto utiliza Spring Security para:
- Autenticação de usuários
- Autorização baseada em roles
- Proteção CSRF

### Autorização
- Método `@userAuthorizationService.canUpdate()` verifica permissões por usuário
- Role `ROLE_admin` possui acesso completo

## Testes

### Executar Testes com Maven

```bash
./mvnw test
```

### Scripts de Teste

#### Teste de Saúde (Smoke Test)
```bash
./src/main/scripts/smoketest.sh
```

#### Testes Integrados
```bash
./src/main/scripts/testeIntegrado.sh
```

#### Testes Funcionais
```bash
./src/main/scripts/testesFuncionais.sh
```

## Pipeline CI/CD

O projeto possui pipeline automatizado configurado no Azure Pipelines (`azure-pipelines.yml`).

### Stages do Pipeline
1. **Build** - Compilação do projeto
2. **Test** - Execução de testes unitários
3. **Integration Tests** - Testes integrados
4. **Functional Tests** - Testes funcionais
5. **SonarCloud Analysis** - Análise de qualidade de código

## Análise de Código

### SonarCloud
O projeto está integrado com SonarCloud para análise de qualidade contínua.

- **Organização**: marianaajpereira
- **URL**: https://sonarcloud.io

## Banco de Dados

### H2 Database
- **URL**: `jdbc:h2:./temp`
- **Usuário**: `sa`
- **Console H2**: http://localhost:5000/h2-console

### Configuração JPA
- `spring.jpa.hibernate.ddl-auto=update` - Atualiza schema automaticamente

## Variáveis de Ambiente

A aplicação pode ser configurada via variáveis de ambiente ou arquivo `application.properties`:

| Propriedade | Padrão | Descrição |
|---|---|---|
| `spring.datasource.url` | `jdbc:h2:./temp` | URL do banco de dados |
| `spring.datasource.username` | `sa` | Usuário do banco |
| `server.port` | `5000` | Porta do servidor |

## Construir para Produção

```bash
./mvnw clean package -DskipTests
```

O JAR será gerado em `target/10cdlr-devops-dev-0.0.2.7-SNAPSHOT.jar`

## Troubleshooting

### Porta Já Está em Uso
Se a porta 5000 estiver em uso, altere em `application.properties`:
```properties
server.port=8080
```

### Problemas de Compilação Java
Certifique-se que possui Java 11+ instalado:
```bash
java -version
```

### Limpar Cache Maven
```bash
./mvnw clean
```

## Contribuindo

1. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
2. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
3. Push para a branch (`git push origin feature/AmazingFeature`)
4. Abra um Pull Request

## Versão

`0.0.2.7-SNAPSHOT` - Em desenvolvimento

## Autor

Mariana Pereira

## Licença

Este projeto está sob licença MIT. Veja o arquivo LICENSE para mais detalhes.

## Suporte

Para reportar bugs ou solicitar features, abra uma issue no repositório.

---

**Última atualização**: 15 de janeiro de 2026
