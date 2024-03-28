package service;

import model.Produit;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/produits")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProduitDAOImplApi implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger logger = Logger.getLogger(CategorieDAOImpl.class.getName());
    private final SessionFactory sessionFactory = getSessionFactory();

    protected SessionFactory getSessionFactory() {
        try {
            return HibernateUtil.getSessionFactory();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Could not create SessionFactory", e);
            throw new IllegalStateException("Could not create SessionFactory");
        }
    }

    @GET
    @Path("/{id}")
    public Produit getProduitById(@PathParam("id") int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Produit produit = null;
        try {
            session.beginTransaction();
            Query<Produit> query = session.createQuery("from Produit where id=:produitId", Produit.class);
            query.setParameter("produitId", id);
            produit = query.uniqueResult();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return produit;
    }




    @GET
    @Path("/")
    public List<Produit> listProduits(){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Produit> productsList = session.createQuery("from Produit", Produit.class).list();
        session.getTransaction().commit();
        return productsList;
    }


    @GET
    @Path("/getAll")
    public List<Map<String, Object>> listProducts2() {
        try {
            Session session = this.sessionFactory.getCurrentSession();
            session.beginTransaction();
            List<Produit> productList = session.createQuery("from Produit ", Produit.class).list();
            session.getTransaction().commit();

            return productList.stream()
                    .map(this::convertToMap)
                    .collect(Collectors.toList());
        }catch (Exception e ){
            System.out.println("test fail + "+e);
        }
       return null;
    }

    private Map<String, Object> convertToMap(Produit product) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", product.getId());
        map.put("designation", product.getDesignation());
        map.put("prix", product.getPrix());
        map.put("quantite", product.getQuantite());
        map.put("sdr", product.getSdr());
        map.put("photo", product.getPhoto());
        if (product.getCategorie() != null) {
            map.put("categoryId", product.getCategorie().getId());
            map.put("categoryNom", product.getCategorie().getNom());
            //System.out.println(product.getCategorie().getNom());
        } else {
            map.put("categoryId", null);
            map.put("categoryName", null);
        }
        //map.put("categoryId", product.getCategorie().);
       // map.put("categoryName", product.getCategorie().getNom());
        return map;
    }
    @PUT
    @Path("/")
    public void updateProduit(Produit produit){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(produit);
        session.getTransaction().commit();
        logger.info("Product successfully updated, Product Details="+produit);
    }

    @POST
    @Path("/")
    public int addProduit(Produit produit) {

        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(produit);
        session.getTransaction().commit();
        logger.info("Product successfully saved, Product Details="+produit);
        return 1;
    }

    @GET
    @Path("/keyword/{keyword}")
    public List<Produit> selectProdByKeyword(@PathParam("keyword")  String keyWord){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Produit> productsList = session.createQuery("from Produit p WHERE p.designation LIKE '%"+keyWord+"%'", Produit.class).list();
        session.getTransaction().commit();
        return productsList;
    }

    @DELETE
    @Path("/{id}")
    public int removeProduct(@PathParam("id") int id) {
        int response =-1;
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Produit produit = session.getReference(Produit.class, Integer.valueOf(id));
        if(null != produit){
            session.delete(produit);
            response=1;
        }
        session.getTransaction().commit();
        logger.info("User deleted successfully, User details="+produit);
        return response;
    }
}
