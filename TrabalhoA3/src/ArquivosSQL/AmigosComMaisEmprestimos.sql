SELECT a.nome, COUNT(e.ID) AS totalEmprestimos
FROM amigo a
JOIN emprestimo e ON a.ID = e.ID_amigo
GROUP BY a.nome
ORDER BY totalEmprestimos DESC;