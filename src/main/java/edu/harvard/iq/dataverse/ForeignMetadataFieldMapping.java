

package edu.harvard.iq.dataverse;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author Leonid Andreev
 */
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames={"foreignMetadataFormatMapping_id","foreignFieldXpath"}) )
@NamedQueries({
  @NamedQuery( name="ForeignMetadataFieldMapping.findByPath",
               query="SELECT fmfm FROM ForeignMetadataFieldMapping fmfm WHERE fmfm.foreignMetadataFormatMapping.name=:formatName AND fmfm.foreignFieldXPath=:xPath")  
})
@Entity
public class ForeignMetadataFieldMapping implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @ManyToOne(cascade = CascadeType.MERGE)
    private ForeignMetadataFormatMapping foreignMetadataFormatMapping;

    @Column(name = "foreignFieldXPath", columnDefinition = "TEXT")
    private String foreignFieldXPath;
    
    @Column(name = "datasetfieldName", columnDefinition = "TEXT")
    private String datasetfieldName;    
    
    @Column(name = "metadatablockName", columnDefinition = "TEXT")
    private String metadatablockName;    
    
    @OneToMany(mappedBy = "parentFieldMapping", cascade = {CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<ForeignMetadataFieldMapping> childFieldMappings;
        
    @ManyToOne(cascade = CascadeType.MERGE)
    private ForeignMetadataFieldMapping parentFieldMapping;
    
    private boolean isAttribute;
    
    /* getters/setters: */

    public ForeignMetadataFormatMapping getForeignMetadataFormatMapping() {
        return foreignMetadataFormatMapping;
    }

    public void setForeignMetadataFormatMapping(ForeignMetadataFormatMapping foreignMetadataFormatMapping) {
        this.foreignMetadataFormatMapping = foreignMetadataFormatMapping;
    }
    
    public String getForeignFieldXPath() {
        return foreignFieldXPath;
    }

    public void setForeignFieldXPath(String foreignFieldXPath) {
        this.foreignFieldXPath = foreignFieldXPath;
    }
    
    public String getDatasetfieldName() {
        return datasetfieldName;
    }

    public void setDatasetfieldName(String datasetfieldName) {
        this.datasetfieldName = datasetfieldName;
    }
    
    public String getMetadatablockName() {
        return metadatablockName;
    }

    public void setMetadatablockName(String metadatablockName) {
        this.metadatablockName = metadatablockName;
    }
    
    public Collection<ForeignMetadataFieldMapping> getChildFieldMappings() {
        return this.childFieldMappings;
    }

    public void setChildFieldMappings(Collection<ForeignMetadataFieldMapping> childFieldMappings) {
        this.childFieldMappings = childFieldMappings;
    }
    
    /*
    public Collection<ForeignMetadataFieldMapping> getAttributeMappings() {
        return this.attributeMappings;
    }

    public void setAttributeMappings(Collection<ForeignMetadataFieldMapping> attributeMappings) {
        this.attributeMappings = attributeMappings;
    }
    */
    
    
    public ForeignMetadataFieldMapping getParentFieldMapping() {
        return parentFieldMapping;
    }

    public void setParentFieldMapping(ForeignMetadataFieldMapping parentFieldMapping) {
        this.parentFieldMapping = parentFieldMapping;
    }
    
    public boolean isAttribute() {
        return isAttribute;
    }

    public void setIsAttribute(boolean isAttribute) {
        this.isAttribute = isAttribute;
    }
    
    /* logical: */
    
    public boolean isChild() {
        return this.parentFieldMapping != null;        
    }    
    
    public boolean HasChildren() {
        return !this.childFieldMappings.isEmpty();
    }
    
    /*
    public boolean HasAttributes() {
        return !this.attributeMappings.isEmpty();
    }
    */

    public boolean HasParent() {
        return this.parentFieldMapping != null;
    }
    /* overrides: */ 

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ForeignMetadataFieldMapping)) {
            return false;
        }
        ForeignMetadataFieldMapping other = (ForeignMetadataFieldMapping) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.harvard.iq.dataverse.ForeignMetadataFieldMapping[ id=" + id + " ]";
    }
    
}
