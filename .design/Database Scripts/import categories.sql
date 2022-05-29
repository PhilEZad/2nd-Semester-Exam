-- ONLY INSERT catID, FK_ParentCat and catName. DO NOT IMPORT tooltips and guide before database can handle those tables 
INSERT INTO Categories(CatID, FK_ParentCat,catName)
SELECT  PK_CategoryID, FK_ParentID, categoryName FROM [Category import]