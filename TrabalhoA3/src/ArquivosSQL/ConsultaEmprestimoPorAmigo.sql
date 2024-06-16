SELECT a.nome, f.nome AS ferramenta, e.data_inicial, e.data_final
FROM amigo a
JOIN emprestimo e ON a.ID = e.ID_amigo
JOIN ferramenta f ON e.ID_ferramenta = f.ID
WHERE e.situacao = 'Em Dia';