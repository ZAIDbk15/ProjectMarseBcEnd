package beans;

import dao.CategorieDAO;
import model.Categorie;
import model.Produit;
import org.apache.commons.io.FileUtils;
import org.primefaces.model.file.UploadedFile;
import service.CategorieDAOImpl;
import service.ProduitDAOImpl;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@ManagedBean(name = "produitCategories", eager = true)
@SessionScoped
public class ProduitCategories implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Produit> allProduit;
    private List<Produit> filteredProduits;
    private List<Categorie> allCategorie;
    private Map<Integer,String> allCategorieByMap;
    private Categorie categorie;
    private CategorieDAO categorService =new CategorieDAOImpl();
    private List<Produit> categoryProducts;
    private Categorie selectedCategorie;
    private Produit selectedProduit;
    private Produit produitToAdd = new Produit();
    private int produit;
    private Produit productToAdd = new Produit();
    private ProduitDAOImpl produitDao = new ProduitDAOImpl();
    private boolean editMode = false;
    private boolean addMode = false;

    private UploadedFile photo;
    public UploadedFile getPhoto() {
        return photo;
    }

    public void setPhoto(UploadedFile photo) {
        String uploadPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + File.separator + "images" + File.separator + "photos";
        this.photo = photo;
        if(this.photo.getSize()>0) {
            try {

                System.out.println("Upload Path  : " + uploadPath);
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                String newName = UUID.randomUUID().toString() + photo.getFileName();
                if (photo != null) {

                    FileUtils.copyInputStreamToFile(photo.getInputStream(), new File(uploadPath + File.separator + newName));
                    if (editMode == true) {
                        selectedProduit.setPhoto(newName);
                    } else {
                        produitToAdd.setPhoto(newName);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private ProduitDAOImpl produitService;
    {
        produitService = new ProduitDAOImpl();
    }

    @PostConstruct
    public void init(){
        allProduit=getAllProduit();
    }

    public void edit(){
        System.out.println("edit clicked");
        editMode=true;
        addMode=false;
    }
    public void cancelUpdate(){
        editMode=false;
    }

    public void prepareAdd(){
        addMode=true;
        editMode=false;
    }
    public void cancelAdd(){
        productToAdd=new Produit();
        addMode=false;
    }

    public void addProduit(){

        if(productToAdd!=null) {
            produitService.addProduit(productToAdd);
            System.out.println("Ajout de la catégorie avec Succès");
            addMessage(FacesMessage.SEVERITY_INFO, "Ajout Réussi", "Ajout de la Produit avec Succès");
        }else {
            addMessage(FacesMessage.SEVERITY_WARN, "Ajout échoué", "Erreur lors de l'ajout de la Produit");
        }
        addMode=false;
    }

    public void updateProduit(){
        if(selectedProduit!=null) {
            System.out.println("Updating... => "+ selectedProduit);
            produitService.updateProduit(selectedProduit);
            System.out.println("Modification de le produit avec Succès");
            addMessage(FacesMessage.SEVERITY_INFO, "Modification Réussie", "Modification de la Produit avec Succès");
        }else {
            addMessage(FacesMessage.SEVERITY_WARN, "Modification échouée", "Erreur lors de la modification de la Produit");
        }
        editMode=false;
    }

    public void deleteSelectedProduit() {
        produitService.removeProduit(selectedProduit.getId());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage("msgDel", new FacesMessage(FacesMessage.SEVERITY_INFO, "Categorie Supprimée", selectedProduit.toString()));
        allProduit=getAllProduit();
    }

    public List<Produit> getAllProduit() {
        allProduit= produitDao.listProduits();
        return allProduit;
    }

    public void setAllProduit(List<Produit> allProduit) {
        this.allProduit = allProduit;
    }

    public List<Produit> getFilteredProduits() {
        return filteredProduits;
    }

    public void setFilteredProduits(List<Produit> filteredProduits) {
        this.filteredProduits = filteredProduits;
    }

    public List<Categorie> getAllCategorie() {
        allCategorie= categorService.listCategories();
        return allCategorie;
    }

    public Map<Integer, String> getAllCategorieByMap() {
        allCategorieByMap=categorService.listCategoriesbyKey();
        return allCategorieByMap;
    }

    public void setAllCategorieByMap(Map<Integer, String> allCategorieByMap) {
        this.allCategorieByMap = allCategorieByMap;
    }

    public void setAllCategorie(List<Categorie> allCategorie) {
        this.allCategorie = allCategorie;
    }

    public List<Produit> getCategoryProducts() {
        return categoryProducts;
    }

    public void setCategoryProducts(List<Produit> categoryProducts) {
        this.categoryProducts = categoryProducts;
    }

    public Produit getSelectedProduit() {
        return selectedProduit;
    }

    public void setSelectedProduit(Produit selectedProduit) {
        this.selectedProduit = selectedProduit;
    }

    public Produit getProduitToAdd() {
        return produitToAdd;
    }

    public void setProduitToAdd(Produit produitToAdd) {
        this.produitToAdd = produitToAdd;
    }

    public int getProduit() {
        return produit;
    }

    public void setProduit(int produit) {
        this.produit = produit;
    }

    public Produit getProductToAdd() {
        return productToAdd;
    }

    public void setProductToAdd(Produit productToAdd) {
        this.productToAdd = productToAdd;
    }

    public ProduitDAOImpl getProduitDao() {
        return produitDao;
    }

    public void setProduitDao(ProduitDAOImpl produitDao) {
        this.produitDao = produitDao;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public boolean isAddMode() {
        return addMode;
    }

    public void setAddMode(boolean addMode) {
        this.addMode = addMode;
    }

    public ProduitDAOImpl getProduitService() {
        return produitService;
    }

    public void setProduitService(ProduitDAOImpl produitService) {
        this.produitService = produitService;
    }
    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public Categorie getSelectedCategorie() {
        return selectedCategorie;
    }

    public void setSelectedCategorie(Categorie selectedCategorie) {
        this.selectedCategorie = selectedCategorie;
    }
}
