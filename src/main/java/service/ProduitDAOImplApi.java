package service;

import model.Produit;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.HibernateUtil;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public Produit getProduitByIdapi(@PathParam("id") int id){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Produit produit = session.getReference(Produit.class, Integer.valueOf(id));
        session.getTransaction().commit();
        return produit;
    }

    @GET
    @Path("/")
    public List<Produit> listProduitsapi(){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Produit> productsList = session.createQuery("from Produit", Produit.class).list();
        session.getTransaction().commit();
        return productsList;
    }
    @PUT
    @Path("/")
    public void updateProduitApi(Produit produit){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.merge(produit);
        session.getTransaction().commit();
        logger.info("Product successfully updated, Product Details="+produit);
    }
    @POST
    @Path("/")
    public void addProduitApi(Produit produit) {
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.persist(produit);
        session.getTransaction().commit();
        logger.info("Product successfully saved, Product Details="+produit);
    }

    @GET
    @Path("/keyword/{keyword}")
    public List<Produit> selectProdByKeywordApi(@PathParam("keyword")  String keyWord){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Produit> productsList = session.createQuery("from Produit p WHERE p.designation LIKE '%"+keyWord+"%'", Produit.class).list();
        session.getTransaction().commit();
        return productsList;
    }
    @DELETE
    @Path("/{id}")
    public void removeProduit( @PathParam("id") int id){
        Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        Produit produit = session.getReference(Produit.class, Integer.valueOf(id));

        if(null != produit){
            session.delete(produit);
        }
        session.getTransaction().commit();
        logger.info("Product deleted successfully, Product details="+produit);
    }
}
