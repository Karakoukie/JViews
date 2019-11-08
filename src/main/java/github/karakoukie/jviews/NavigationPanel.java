/*
 * Copyright (C) 2019 Tristan Muller (tristan.muller@cirad.fr)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package github.karakoukie.jviews;

import javax.swing.JButton;

/**
 *
 * @since 8 nov. 2019
 * @author Tristan Muller (tristan.muller@cirad.fr)
 */
public class NavigationPanel extends MarginPanel {

    /* ------------------------------------------------------------------ */
    /* Fields                                                             */
    /* ------------------------------------------------------------------ */

    private final TitledPanel contentPanel;
    private final JButton backButton;
    private final JButton nextButton;
    private final JButton finishButton;
    private final JButton cancelButton;
    
    /* ------------------------------------------------------------------ */
    /* Constructors                                                       */
    /* ------------------------------------------------------------------ */

    public NavigationPanel() {
        super();
        
        this.contentPanel = new TitledPanel(null);
        this.backButton = new JButton("< Back");
        this.nextButton = new JButton("Next >");
        this.finishButton = new JButton("Finish");
        this.cancelButton = new JButton("Cancel");
        
        backButton.setEnabled(false);
        finishButton.setEnabled(false);
        
        contentPanel.getContentPanel().add(backButton);
        contentPanel.getContentPanel().add(nextButton);
        contentPanel.getContentPanel().add(finishButton);
        contentPanel.getContentPanel().add(cancelButton);
        
        add(contentPanel);
    }
    
    /* ------------------------------------------------------------------ */
    /* Methods                                                            */
    /* ------------------------------------------------------------------ */
    
    public final void enableBackButton(final boolean enabled) {
        backButton.setEnabled(enabled);
    }
    
    public final void enableNextButton(final boolean enabled) {
        nextButton.setEnabled(enabled);
    }
    
    public final void enableFinishButton(final boolean enabled) {
        finishButton.setEnabled(enabled);
    }
    
    public final void enableCancelButton(final boolean enabled) {
        cancelButton.setEnabled(enabled);
    }
    
    public final void addBackEvent(final Runnable event) {
        if (event != null) {
            backButton.addActionListener((e) -> {
                event.run();
            });
        }
    }
    
    public final void addNextEvent(final Runnable event) {
        if (event != null) {
            nextButton.addActionListener((e) -> {
                event.run();
            });
        }
    }
    
    public final void addFinishEvent(final Runnable event) {
        if (event != null) {
            finishButton.addActionListener((e) -> {
                event.run();
            });
        }
    }
    
    public final void addCancelEvent(final Runnable event) {
        if (event != null) {
            cancelButton.addActionListener((e) -> {
                event.run();
            });
        }
    }
    
    /* ------------------------------------------------------------------ */
    /* Overriden methods                                                  */
    /* ------------------------------------------------------------------ */

    /* ------------------------------------------------------------------ */
    /* Setteurs                                                           */
    /* ------------------------------------------------------------------ */

    /* ------------------------------------------------------------------ */
    /* Getteurs                                                           */
    /* ------------------------------------------------------------------ */

    /* ------------------------------------------------------------------ */
    /* Others methods                                                     */
    /* ------------------------------------------------------------------ */

}
