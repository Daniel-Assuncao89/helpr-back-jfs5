USE helpr;

DELIMITER //

CREATE PROCEDURE proc_lista_chamado_aberto_hoje(data_abertura date) 
BEGIN 
 SELECT * FROM chamado WHERE data_abertura = CURRENT_DATE;
END //

DELIMITER ;

call proc_lista_chamado_aberto_hoje(current_date());
DROP procedure proc_lista_chamado_aberto_hoje;

