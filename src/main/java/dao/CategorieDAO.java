package dao;

import java.util.List;
import java.util.Map;

import model.Categorie;

public interface CategorieDAO {
    public void addCategorie(Categorie categorie);
    public void updateCategorie(Categorie categorie);
    public List<Categorie> listCategories();
    public Map<Integer,String> listCategoriesbyKey();
    public List<Categorie> selectCatByKeyword(String keyWord);
    public Categorie getCategorieById(int id);
    public void removeCategorie(int id);
}
