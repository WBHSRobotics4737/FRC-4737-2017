
package org.usfirst.frc.team4737.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.*;
import org.usfirst.frc.team4737.lib.FasterIterativeRobot;
import org.usfirst.frc.team4737.robot.commands.MakeRobotPushable;
import org.usfirst.frc.team4737.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends FasterIterativeRobot {

    public static OI OI;

    public static final Drive DRIVE = new Drive();
    public static final Intake INTAKE = new Intake();
    public static final Agitator AGITATOR = new Agitator();
    public static final Shooter SHOOTER_L = new Shooter(Shooter.Side.LEFT);
    public static final Shooter SHOOTER_R = new Shooter(Shooter.Side.RIGHT);
    public static final Feeder FEEDER_L = new Feeder(Shooter.Side.LEFT);
    public static final Feeder FEEDER_R = new Feeder(Shooter.Side.RIGHT);
    public static final Climber CLIMBER = new Climber();

    public static final JetsonTX1 JETSON_TX1 = new JetsonTX1();

    public static final PowerDistributionPanel PDP = new PowerDistributionPanel();

//    Command autonomousCommand;
//    SendableChooser chooser;

    private SendableChooser<Shooter> shooterTuningChooser;
    private Shooter selectedShooter = SHOOTER_L;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        OI = new OI();

//        chooser = new SendableChooser();
//        chooser.addDefault("Default Auto", new ExampleCommand());
//        chooser.addObject("My Auto", new MyAutoCommand());
//        SmartDashboard.putData("Auto mode", chooser);

        shooterTuningChooser = new SendableChooser<>();
        shooterTuningChooser.addDefault("Left Shooter", SHOOTER_L);
        shooterTuningChooser.addObject("Right Shooter", SHOOTER_R);
        SmartDashboard.putData("ShooterChooser", shooterTuningChooser);
    }

    @Override
    public void robotPeriodic() {
        selectedShooter = shooterTuningChooser.getSelected();

        selectedShooter.getSmartDashboardPIDFvals();
        SmartDashboard.putString("shooterTalon", "" +
                selectedShooter.getTarget() + ":" +
                selectedShooter.getSpeed() + ":" +
                selectedShooter.getClosedLoopError()
        );

        StringBuilder sb = new StringBuilder(100);
        for (int channel = 0; channel < 16; channel++) {
            if (channel >= 6 && channel <= 9) continue;

            sb.append(PDP.getCurrent(channel));
            if (channel < 15)
                sb.append(":");
        }
        SmartDashboard.putString("currents", sb.toString());
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    public void disabledInit() {
        new MakeRobotPushable().start();
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select between different autonomous modes using
     * the dashboard. The sendable chooser code works with the Java SmartDashboard.
     * <p>
     * You can add additional auto modes by adding additional commands to the chooser code above (like the commented
     * example) or additional comparisons to the switch structure below with additional strings & commands.
     */
    public void autonomousInit() {
//        autonomousCommand = (Command) chooser.getSelected();

        // schedule the autonomous command (example)
//        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.

//        if (autonomousCommand != null) autonomousCommand.cancel();

        SmartDashboard.putString("currents", "0:1:2:3:4:5:6:7:8:9:10:11:12:13:14:15");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }

}
