CREATE TABLE Composants(
    idComposant VARCHAR PRIMARY KEY,
    nom VARCHAR,
    premiere BOOLEAN,
    produit BOOLEAN,
    prixUnitaire DOUBLE PRECISION
);

CREATE SEQUENCE seqcomposants
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

CREATE TABLE Fabrications(
    idFabrication VARCHAR PRIMARY KEY,
    idComposition VARCHAR REFERENCES Composants (idComposant),
    prixDeRevient DOUBLE PRECISION,
    quantite DOUBLE PRECISION,
    date DATE
);

CREATE SEQUENCE seqfabrication
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

CREATE VIEW Produit AS 
    SELECT *
    FROM Composants
    WHERE produit = true;

INSERT INTO Compositions(idComposition, idComposant, quantite) VALUES
    ('C007', 'C006', 0.2),
    ('C007', 'C004', 0.8),
    ('C007', 'C005', 0.3),
    ('C004', 'C001', 0.5),
    ('C004', 'C002', 0.2),
    ('C004', 'C003', 0.3),
    ('C006', 'C008', 0.5),
    ('C006', 'C009', 0.3);