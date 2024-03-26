package dao;

import model.Categorie;
import model.Produit;

import java.util.List;

public interface ProduitDAO {
    public void addProduit(Produit produit);
    public void updateProduit(Produit produit);
    public List<Produit> listProduits();
    public List<Produit> listProduitsByCategorie(Categorie categ);
    public List<Produit> selectProdByKeyword(String keyWord);
    public Produit getProduitById(int id);
    public void removeProduit(int id);
}