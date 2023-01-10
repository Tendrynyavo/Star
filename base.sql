CREATE TABLE Composants(
    idComposant VARCHAR PRIMARY KEY,
    nom VARCHAR,
    premiere BOOLEAN,
    produit BOOLEAN,
    prixUnitaire DOUBLE PRECISION
);

CREATE SEQUENCE seqcomposants
    AS integer
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 100;

CREATE FUNCTION getcomposantsPK() RETURNS integer
    LANGUAGE plpgsql
    AS $$
    declare composants integer;
BEGIN
    SELECT nextval('seqcomposants') INTO composants;
    RETURN composants;
END;
$$;

CREATE TABLE Compositions(
    idComposition VARCHAR REFERENCES Composants (idComposant),
    idComposant VARCHAR REFERENCES Composants (idComposant),
    quantite DOUBLE PRECISION
);

CREATE TABLE Fabrication(
    idFabrication VARCHAR PRIMARY KEY,
    idComposition VARCHAR REFERENCES students (student_id),
    prixDeRevient DOUBLE PRECISION,
    quantite DOUBLE PRECISION
);

CREATE SEQUENCE seqfabrication
    AS integer
    START WITH 1
    INCREMENT BY 1
    MAXVALUE 100;

CREATE FUNCTION getfabricationPK() RETURNS integer
    LANGUAGE plpgsql
    AS $$
    declare fabrication integer;
BEGIN
    SELECT nextval('seqfabrication') INTO fabrication;
    RETURN fabrication;
END;
$$;

CREATE VIEW Melange AS 
    SELECT compo.idComposant, c.nom, c.premiere, c.produit, c.prixUnitaire, compo.quantite, compo.idComposition
    FROM Compositions compo
        JOIN Composants c ON c.idComposant=compo.idComposant;

CREATE VIEW Melange AS 
    SELECT *
    FROM Composants
    WHERE produit = true;