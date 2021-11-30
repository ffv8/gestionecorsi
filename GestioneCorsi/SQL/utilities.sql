-- Funzione calcolo differenza date

-- !!!! eseguire solo su SQLPLUS !!!!!! 

CREATE OR REPLACE FUNCTION workingdays(start_date IN DATE , end_date IN DATE) RETURN number AS
    total_days number;
    v_count int := 0;
    end_value date := TRUNC(end_date);
    start_value date := TRUNC(start_date);
    date_diff NUMBER;
BEGIN
    WHILE start_value <= end_value LOOP
        IF TO_CHAR(start_value, 'fmDay', 'NLS_DATE_LANGUAGE = italian') IN ('Sabato','Domenica') THEN
        v_count := v_count + 1;
      END IF;
      start_value := start_value + 1;
    END LOOP;
    date_diff := end_value - TRUNC(start_date);
    total_days := date_diff - v_count + 1;
    RETURN total_days;
END;
/