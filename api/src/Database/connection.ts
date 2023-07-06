import mysql from "mysql";

/*const create_connection = () => {
  const mysql_connection = mysql.createConnection({
    //Parametros de conexao do banco de dados
    host: "br122.hostgator.com.br",
    user: "brain408_local_access",
    password: "Eava@141158",
    database: "brain408_brainmarket2",
  });
  return mysql_connection;
};*/
//Cria a conexão com banco de dados, por enquanto, o banco é apenas local
const create_connection = () => {
  const mysql_connection = mysql.createConnection({
    //Parametros de conexao do banco de dados
    host: "localhost",
    user: "root",
    password: "123456",
    database: "samambaia",
  });
  return mysql_connection;
};
//Recebe uma string com o comando em SQL e executa, o resultado deve ser acessado atraves de um ".them()"
const executeSQLSelect = (sql_query: string) => {
  const mysql_connection = create_connection();
  return new Promise((data) =>
    mysql_connection.query(sql_query, function (err, result) {
      if (err) console.log(err);
      //console.log(result);
      data(result);
    })
  ).then((r) => {
    mysql_connection.end();
    return r;
  });
};
//Recebe uma string com o comando sql, e os valores que serão inseridos no banco de dados
const executeSQLInsert = (sql_query: string, values: (string | number)[]) => {
  console.log(sql_query);
  console.log(values);
  const mysql_connection = create_connection();
  return new Promise((data) =>
    mysql_connection.query(sql_query, values, function (err, result) {
      if (err) console.log(err);
      else {
        console.log("Isert realizado com sucesso");
      }
      //console.log(result);
      data(result);
    })
  ).then((r:any) => {
    mysql_connection.end();
    console.log(r.insertId);
    return r.insertId;
  });
};

export { executeSQLSelect, executeSQLInsert };
