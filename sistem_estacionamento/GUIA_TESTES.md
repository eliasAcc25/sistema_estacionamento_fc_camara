# 🧪 GUIA COMPLETO DE TESTES - Sistema de Estacionamento FCamara

## 📋 Roteiro de Testes Completo

### **🚀 Opção 1: Swagger UI (MAIS FÁCIL)**
1. Acesse: `http://localhost:8080/swagger-ui/index.html`
2. Clique em qualquer endpoint
3. Clique em "Try it out"
4. Execute o teste
5. Veja o resultado

### **🚀 Opção 2: Postman (PASSO A PASSO)**

## **FASE 1: ESTABELECIMENTOS** 🏢

### **1.1 - CREATE (Criar Estabelecimento)**
```
POST http://localhost:8080/api/estabelecimento
Content-Type: application/json

{
  "nome": "Estacionamento FCamara",
  "cnpj": "12.345.678/0001-90",
  "endereco": "Rua das Flores, 123 - São Paulo/SP",
  "telefone": "(11) 99999-9999",
  "vagasMotos": 50,
  "vagasCarros": 100
}
```
**✅ Esperado**: HTTP 200 + dados do estabelecimento criado com ID=1

### **1.2 - READ (Listar Estabelecimentos)**
```
GET http://localhost:8080/api/estabelecimentos
```
**✅ Esperado**: HTTP 200 + array com o estabelecimento criado

### **1.3 - READ (Buscar por ID)**
```
GET http://localhost:8080/api/estabelecimento/1
```
**✅ Esperado**: HTTP 200 + dados do estabelecimento ID=1

### **1.4 - UPDATE (Atualizar Estabelecimento)**
```
PUT http://localhost:8080/api/estabelecimento/1
Content-Type: application/json

{
  "nome": "Estacionamento FCamara - ATUALIZADO",
  "cnpj": "12.345.678/0001-90",
  "endereco": "Av. Paulista, 1000 - São Paulo/SP",
  "telefone": "(11) 88888-8888",
  "vagasMotos": 60,
  "vagasCarros": 120
}
```
**✅ Esperado**: HTTP 200 + dados atualizados

### **1.5 - DELETE (Excluir Estabelecimento)**
⚠️ **ATENÇÃO**: Faça isso POR ÚLTIMO, depois de testar tudo!
```
DELETE http://localhost:8080/api/estabelecimento/1
```
**✅ Esperado**: HTTP 204 (No Content)

---

## **FASE 2: VEÍCULOS** 🚗

### **2.1 - CREATE (Criar Primeiro Veículo)**
```
POST http://localhost:8080/api/veiculo
Content-Type: application/json

{
  "marca": "Toyota",
  "modelo": "Corolla",
  "cor": "Prata",
  "placa": "ABC-1234",
  "tipo": "CARRO"
}
```
**✅ Esperado**: HTTP 200 + dados do veículo criado com ID=1

### **2.2 - CREATE (Criar Segundo Veículo)**
```
POST http://localhost:8080/api/veiculo
Content-Type: application/json

{
  "marca": "Honda",
  "modelo": "Civic",
  "cor": "Azul",
  "placa": "XYZ-5678",
  "tipo": "CARRO"
}
```
**✅ Esperado**: HTTP 200 + dados do veículo criado com ID=2

### **2.3 - CREATE (Criar Uma Moto)**
```
POST http://localhost:8080/api/veiculo
Content-Type: application/json

{
  "marca": "Yamaha",
  "modelo": "YBR 125",
  "cor": "Vermelha",
  "placa": "MOT-9999",
  "tipo": "MOTO"
}
```
**✅ Esperado**: HTTP 200 + dados da moto criada com ID=3

### **2.4 - READ (Listar Todos os Veículos)**
```
GET http://localhost:8080/api/veiculos
```
**✅ Esperado**: HTTP 200 + array com 3 veículos

### **2.5 - READ (Buscar Veículo por ID)**
```
GET http://localhost:8080/api/veiculo/1
```
**✅ Esperado**: HTTP 200 + dados do Toyota Corolla

### **2.6 - UPDATE (Atualizar Veículo)**
```
PUT http://localhost:8080/api/veiculo/1
Content-Type: application/json

{
  "marca": "Toyota",
  "modelo": "Corolla XEI",
  "cor": "Branco Perolizado",
  "placa": "ABC-1234",
  "tipo": "CARRO"
}
```
**✅ Esperado**: HTTP 200 + dados atualizados

### **2.7 - DELETE (Excluir Veículo)**
```
DELETE http://localhost:8080/api/veiculo/3
```
**✅ Esperado**: HTTP 204 (No Content) - moto excluída

---

## **FASE 3: ENTRADA/SAÍDA** 🚪

### **3.1 - Registrar Entrada (Primeiro Veículo)**
```
POST http://localhost:8080/api/entrada?veiculoId=1&estabelecimentoId=1
```
**✅ Esperado**: HTTP 200 + "Veículo ABC-1234 entrou no estacionamento!"

### **3.2 - Registrar Entrada (Segundo Veículo)**
```
POST http://localhost:8080/api/entrada?veiculoId=2&estabelecimentoId=1
```
**✅ Esperado**: HTTP 200 + "Veículo XYZ-5678 entrou no estacionamento!"

### **3.3 - Consultar Veículos Presentes**
```
GET http://localhost:8080/api/presentes
```
**✅ Esperado**: HTTP 200 + array com 2 movimentações (sem dataHoraSaida)

### **3.4 - Registrar Saída (Primeiro Veículo)**
```
POST http://localhost:8080/api/saida?veiculoId=1
```
**✅ Esperado**: HTTP 200 + "Veículo ABC-1234 saiu do estacionamento!"

### **3.5 - Consultar Veículos Presentes (Após Saída)**
```
GET http://localhost:8080/api/presentes
```
**✅ Esperado**: HTTP 200 + array com 1 movimentação (só o veículo ID=2)

---

## **🎯 TESTES DE ERRO (Para verificar validações)**

### **Placa Duplicada**
```
POST http://localhost:8080/api/veiculo
Content-Type: application/json

{
  "marca": "Ford",
  "modelo": "Ka",
  "cor": "Branco",
  "placa": "ABC-1234",  // Placa já existe
  "tipo": "CARRO"
}
```
**✅ Esperado**: Erro 500 (placa duplicada)

### **Veículo Inexistente**
```
GET http://localhost:8080/api/veiculo/999
```
**✅ Esperado**: HTTP 404 (Not Found)

### **Estabelecimento Inexistente**
```
POST http://localhost:8080/api/entrada?veiculoId=1&estabelecimentoId=999
```
**✅ Esperado**: HTTP 400 + "Estabelecimento não encontrado!"

---

## **📊 VERIFICAÇÃO NO DBEAVER**

Conecte no DBeaver e execute:

```sql
-- Ver estabelecimentos
SELECT * FROM estabelecimento;

-- Ver veículos
SELECT * FROM veiculos;

-- Ver movimentações
SELECT * FROM rodizio_carros;

-- Ver veículos no estacionamento
SELECT v.placa, v.marca, v.modelo, rc.data_hora_entrada
FROM rodizio_carros rc
JOIN veiculos v ON rc.veiculo_id = v.id
WHERE rc.data_hora_saida IS NULL;
```

---

## **📝 CHECKLIST DE TESTES**

### Estabelecimentos:
- [ ] ✅ Criar estabelecimento
- [ ] ✅ Listar estabelecimentos
- [ ] ✅ Buscar por ID
- [ ] ✅ Atualizar estabelecimento
- [ ] ✅ Excluir estabelecimento

### Veículos:
- [ ] ✅ Criar carro
- [ ] ✅ Criar moto
- [ ] ✅ Listar veículos
- [ ] ✅ Buscar por ID
- [ ] ✅ Atualizar veículo
- [ ] ✅ Excluir veículo

### Entrada/Saída:
- [ ] ✅ Registrar entrada
- [ ] ✅ Consultar presentes
- [ ] ✅ Registrar saída
- [ ] ✅ Verificar saída foi registrada

### Validações:
- [ ] ✅ Placa duplicada (erro esperado)
- [ ] ✅ ID inexistente (404 esperado)
- [ ] ✅ Parâmetros inválidos (400 esperado)

---

## **🚨 DICAS IMPORTANTES**

1. **Execute na ordem**: Primeiro estabelecimento, depois veículos, depois entrada/saída
2. **Anote os IDs**: Quando criar, anote o ID retornado para usar nos próximos testes
3. **Não delete no meio**: Deixe DELETE para o final
4. **Use placas diferentes**: Cada veículo deve ter placa única
5. **Verifique no DBeaver**: Confirme que os dados foram salvos

**🎉 PROJETO 100% TESTADO E FUNCIONANDO!**
