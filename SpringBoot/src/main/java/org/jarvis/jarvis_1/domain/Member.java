package org.jarvis.jarvis_1.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Member
 */
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
// @ToString(exclude = "productList")
@Table(name = "tbl_member")
public class Member {

    @Id
    private String mid;

    private String mpw;

    // @OneToMany(mappedBy = "member")
    // private List<Product> productList;
}