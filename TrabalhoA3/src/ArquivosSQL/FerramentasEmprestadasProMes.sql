SELECT DATE_FORMAT(data_inicial, '%Y-%m') AS mes,
       COUNT(*) AS totalEmprestimos
FROM emprestimo
GROUP BY DATE_FORMAT(data_inicial, '%Y-%m')
ORDER BY mes;