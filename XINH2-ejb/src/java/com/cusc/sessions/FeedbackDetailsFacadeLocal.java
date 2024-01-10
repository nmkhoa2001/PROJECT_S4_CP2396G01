/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.cusc.sessions;

import com.cusc.entities.FeedbackDetails;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ngomi
 */
@Local
public interface FeedbackDetailsFacadeLocal {

    void create(FeedbackDetails feedbackDetails);

    void edit(FeedbackDetails feedbackDetails);

    void remove(FeedbackDetails feedbackDetails);

    FeedbackDetails find(Object id);

    List<FeedbackDetails> findAll();

    List<FeedbackDetails> findRange(int[] range);

    int count();
    
}
