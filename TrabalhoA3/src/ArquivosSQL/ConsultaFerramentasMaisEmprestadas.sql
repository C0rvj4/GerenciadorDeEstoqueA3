SELECT f.nome, COUNT(e.ID) AS totalEmprestimos
FROM ferramenta f
JOIN emprestimo e ON f.ID = e.ID_ferramenta
GROUP BY f.nome
ORDER BY totalEmprestimos DESC;
