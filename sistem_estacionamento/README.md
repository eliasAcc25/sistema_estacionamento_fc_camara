# Sistema de Estacionamento FCamara 🚗

Sistema de gerenciamento de estacionamento para carros e motos desenvolvido para o teste técnico da FCamara.

## 🚀 Tecnologias Utilizadas

- **Java 21** - Linguagem de programação
- **Spring Boot 3.5.5** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Web** - API REST
- **Spring Validation** - Validação de dados
- **H2 Database** - Banco de dados em memória/arquivo
- **Lombok** - Redução de código boilerplate
- **Maven** - Gerenciamento de dependências
- **Swagger/OpenAPI 3** - Documentação da API

## 📋 Funcionalidades

### Estabelecimento (CRUD Completo)
- ✅ Criar estabelecimento
- ✅ Listar estabelecimentos
- ✅ Buscar estabelecimento por ID
- ✅ Atualizar estabelecimento
- ✅ Excluir estabelecimento

### Veículos (CRUD Completo)
- ✅ Cadastrar veículo
- ✅ Listar veículos
- ✅ Buscar veículo por ID
- ✅ Atualizar veículo
- ✅ Excluir veículo

### Controle de Entrada/Saída
- ✅ Registrar entrada de veículo
- ✅ Registrar saída de veículo
- ✅ Consultar veículos presentes no estacionamento

## 🛠️ Pré-requisitos

- **Java 21** ou superior
- **Maven 3.6+** (ou usar o Maven Wrapper incluído)
- **Git** (opcional, para clonar o repositório)

## ⚙️ Configuração e Execução

### 1. Clonar o Repositório
```bash
git clone <url-do-repositorio>
cd sistem_estacionamento
```

### 2. Executar a Aplicação

#### Via Maven Wrapper (Recomendado)
```bash
# Windows
.\mvnw.cmd spring-boot:run

# Linux/Mac
./mvnw spring-boot:run
```

#### Via Maven Instalado
```bash
mvn spring-boot:run
```

### 3. Verificar se a Aplicação Subiu
- Acesse: `http://localhost:8080/api/estabelecimentos`
- Se retornar `[]`, a aplicação está funcionando!

## 🗄️ Banco de Dados

### Configuração H2
- **URL**: `jdbc:h2:file:./data/estacionamento`
- **Usuário**: `sa`
- **Senha**: `sa`
- **Console H2**: `http://localhost:8080/h2-console`

### Configuração para DBeaver
1. Driver: H2 Database
2. URL: `jdbc:h2:file:C:/caminho/para/projeto/data/estacionamento`
3. Usuário: `sa`
4. Senha: `sa`

## 📡 API Endpoints

### Base URL
```
http://localhost:8080/api
```

### 🏢 Estabelecimento

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/estabelecimento` | Criar estabelecimento |
| GET | `/estabelecimentos` | Listar todos |
| GET | `/estabelecimento/{id}` | Buscar por ID |
| PUT | `/estabelecimento/{id}` | Atualizar |
| DELETE | `/estabelecimento/{id}` | Excluir |

#### Exemplo JSON - Estabelecimento
```json
{
  "nome": "Estacionamento FCamara",
  "cnpj": "12.345.678/0001-90",
  "endereco": "Rua das Flores, 123 - São Paulo/SP",
  "telefone": "(11) 99999-9999",
  "vagasMotos": "50",
  "vagasCarros": "100"
}
```

### 🚗 Veículos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/veiculo` | Cadastrar veículo |
| GET | `/veiculos` | Listar todos |
| GET | `/veiculo/{id}` | Buscar por ID |
| PUT | `/veiculo/{id}` | Atualizar |
| DELETE | `/veiculo/{id}` | Excluir |

#### Exemplo JSON - Veículo
```json
{
  "marca": "Toyota",
  "modelo": "Corolla",
  "cor": "Prata",
  "placa": "ABC-1234",
  "tipo": "CARRO"
}
```

**Tipos de Veículo:** `CARRO` ou `MOTO`

### 🚪 Entrada/Saída

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| POST | `/entrada?veiculoId={id}&estabelecimentoId={id}` | Registrar entrada |
| POST | `/saida?veiculoId={id}` | Registrar saída |
| GET | `/presentes` | Veículos no estacionamento |

## 📖 Documentação da API

### Swagger UI (OpenAPI 3)
A documentação interativa da API está disponível em:
```
http://localhost:8080/swagger-ui/index.html
```

**Funcionalidades do Swagger:**
- ✅ Documentação completa de todos os endpoints
- ✅ Exemplos de JSON para requisições
- ✅ Teste interativo dos endpoints
- ✅ Descrição detalhada de cada campo
- ✅ Códigos de resposta HTTP documentados

### OpenAPI JSON
Para integração com outras ferramentas:
```
http://localhost:8080/v3/api-docs
```

### Console H2
Acesse o banco de dados em:
```
http://localhost:8080/h2-console
```

## 🧪 Testando com Postman

### 1. Criar Estabelecimento
```http
POST http://localhost:8080/api/estabelecimento
Content-Type: application/json

{
  "nome": "Estacionamento Central",
  "cnpj": "12.345.678/0001-90",
  "endereco": "Av. Paulista, 1000",
  "telefone": "(11) 3333-3333",
  "vagasMotos": "20",
  "vagasCarros": "50"
}
```

### 2. Cadastrar Veículo
```http
POST http://localhost:8080/api/veiculo
Content-Type: application/json

{
  "marca": "Honda",
  "modelo": "Civic",
  "cor": "Branco",
  "placa": "XYZ-9876",
  "tipo": "CARRO"
}
```

### 3. Registrar Entrada
```http
POST http://localhost:8080/api/entrada?veiculoId=1&estabelecimentoId=1
```

### 4. Registrar Saída
```http
POST http://localhost:8080/api/saida?veiculoId=1
```

### 5. Consultar Veículos Presentes
```http
GET http://localhost:8080/api/presentes
```

## 📁 Estrutura do Projeto

```
src/main/java/Fcamara/sistem_estacionamento/
├── controller/
│   └── EstabelecimentoController.java    # Endpoints da API
├── model/
│   ├── Estabelecimento.java              # Entidade Estabelecimento
│   ├── Veiculos.java                     # Entidade Veículos
│   ├── Movimentacao.java                 # Entidade Movimentação
│   └── TipoVeiculo.java                  # Enum tipos de veículo
├── repository/
│   ├── EstabelecimentoRepository.java    # Repositório JPA
│   ├── VeiculoRepository.java            # Repositório JPA
│   └── MovimentacaoRepository.java       # Repositório JPA
└── SistemaEstacionamentoApplication.java # Classe principal
```

## 🔧 Configurações

### application.properties
```properties
# Servidor
server.port=8080

# Desabilitar Docker Compose
spring.docker.compose.enabled=false

# Banco H2
spring.datasource.url=jdbc:h2:file:./data/estacionamento
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=sa

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Console H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
```

## 🚨 Resolução de Problemas

### Erro: "Cannot load driver class: org.h2.Driver"
- **Solução**: Verificar se a dependência H2 está com `scope=runtime` no pom.xml

### Erro: "Docker process start exception"
- **Solução**: Adicionar `spring.docker.compose.enabled=false` no application.properties

### Porta 8080 em uso
- **Solução**: Alterar `server.port=8081` no application.properties

### Aplicação não inicia
1. Verificar se o Java 21 está instalado: `java -version`
2. Limpar e recompilar: `.\mvnw.cmd clean install`
3. Verificar se não há conflitos de porta

## 📊 Modelo de Dados

### Tabelas Criadas Automaticamente
- `estabelecimento` - Dados dos estabelecimentos
- `veiculos` - Cadastro de veículos
- `rodizio_carros` - Controle de movimentação

## 🎯 Validações Implementadas

### Estabelecimento
- Todos os campos são obrigatórios
- CNPJ, nome, endereço, telefone
- Quantidade de vagas para carros e motos

### Veículos
- Todos os campos são obrigatórios
- Placa única no sistema
- Tipo deve ser CARRO ou MOTO
- Marca, modelo e cor obrigatórios

### Movimentação
- Veículo deve existir no sistema
- Estabelecimento deve existir no sistema
- Controle automático de entrada/saída

## 👨‍💻 Autor

Desenvolvido para o teste técnico da FCamara - Sistema de Estacionamento

---

**🚀 API 100% funcional e aderente aos requisitos do teste técnico!**
